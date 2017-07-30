package org.eforum.front.controller;

import java.io.File;

import javax.servlet.http.HttpServletResponse;

import org.eforum.constant.Constants;
import org.eforum.service.FileService;
import org.eforum.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 文件controller
 * 
 * @author Sackr
 *
 */
@Controller
@RequestMapping(value = "/file")
public class FileController extends BaseController {
	@Autowired
	private FileService fileService;

	@RequestMapping(value = "/downloadAnonymousHeadPortrait", method = RequestMethod.GET)
	@Transactional
	public void downloadAnonymousHeadPortrait(HttpServletResponse response) {
		File file = fileService.getFile(Constants.HEAD_PORTRAIT_DIR, Constants.ANONYMOUS_HEAD_PORTRAIT_FILE_NAME);
		FileUtil.writeFileToResponse(file, response);
	}
}
