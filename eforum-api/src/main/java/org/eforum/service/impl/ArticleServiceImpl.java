package org.eforum.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.eforum.constant.Constants;
import org.eforum.entity.Article;
import org.eforum.exception.ServiceException;
import org.eforum.repository.ArticleRepository;
import org.eforum.service.ArticleService;
import org.eforum.service.FileService;
import org.eforum.util.EntityUtil;
import org.eforum.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private FileService fileService;

	@Override
	public Page<Article> listArticle(int pageNumber, int pageSize) {
		return articleRepository.findAll(new PageRequest(pageNumber - 1, pageSize));
	}

	@Override
	public Article findArticleById(Long id) {
		Article article = articleRepository.findOne(id);
		if (null == article) {
			throw new ServiceException("未找到该帖子，或者该帖子已被删除！");
		}
		return article;
	}

	@Override
	public List<Article> findSuggestionArticle(int pageSize) {
		PageRequest pageRequest = new PageRequest(0, pageSize);
		Page<Article> page = articleRepository.findAll((root, query, cb) -> {
			query.orderBy(cb.asc(root.get("weight").as(Integer.class)));
			return query.getRestriction();
		}, pageRequest);
		return page.getContent();
	}

	@Override
	public Article saveOrUpdate(Article article) {
		Article willSaveArticle = null;
		if (StringUtils.isNullOrEmpty(article.getTitle()) || StringUtils.isNullOrEmpty(article.getContent())) {
			throw new ServiceException("标题或内容不能是空的！");
		}
		if (!article.isNew()) {
			willSaveArticle = articleRepository.findOne(article.getId());
			if (null == willSaveArticle) {
				throw new ServiceException("未找到id为【" + article.getId() + "】的帖子");
			}
			EntityUtil.copyPropertiesOfEntity(article, willSaveArticle);
		} else {
			willSaveArticle = article;
		}
		articleRepository.save(willSaveArticle);
		return willSaveArticle;
	}

	@Override
	public List<String> saveImagesOfArticle(MultipartFile[] images) {
		List<String> imageNames = fileService.saveImages(images);
		List<String> imageRequestPaths = new ArrayList<String>();
		for (String imageName : imageNames) {
			imageRequestPaths.add(Constants.IMAGE_REQUEST_PATH + imageName);
		}
		return imageRequestPaths;
	}

	@Override
	public void downloadImageOfArticle(String imageName, HttpServletResponse response) {
		File file = fileService.getImageFileByName(imageName);
		try {
			InputStream is = new FileInputStream(file);
			OutputStream os = new BufferedOutputStream(response.getOutputStream());
			byte[] data = new byte[1024];
			int length;
			while ((length = is.read(data)) != -1) {
				os.write(data, 0, length);
			}
			is.close();
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}