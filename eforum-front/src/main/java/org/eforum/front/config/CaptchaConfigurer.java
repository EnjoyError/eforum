package org.eforum.front.config;

import com.octo.captcha.CaptchaFactory;
import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.component.image.color.ColorGenerator;
import com.octo.captcha.component.image.color.SingleColorGenerator;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.DecoratedRandomTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.textpaster.textdecorator.BaffleTextDecorator;
import com.octo.captcha.component.image.textpaster.textdecorator.TextDecorator;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.engine.CaptchaEngine;
import com.octo.captcha.engine.GenericCaptchaEngine;
import com.octo.captcha.image.ImageCaptchaFactory;
import com.octo.captcha.image.gimpy.GimpyFactory;
import com.octo.captcha.service.image.ImageCaptchaService;
import com.octo.captcha.service.multitype.GenericManageableCaptchaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.*;

@Configuration
public class CaptchaConfigurer {
    private static final Logger LOG = LoggerFactory.getLogger(CaptchaConfigurer.class);

    @Bean
    public ImageCaptchaService imageCaptchaService() {
        LOG.info("配置captcha");
        Color colorDimGrey = new Color(105, 105, 105);
        Color colorWrite = new Color(255, 255, 255);
        ColorGenerator colorGenerator = new SingleColorGenerator(colorDimGrey);
        BaffleTextDecorator baffleDecorator = new BaffleTextDecorator(1, colorWrite);
        // 参数: 最大字符长度，最小字符长度，文本颜色，文本混淆
        TextPaster textPaster = new DecoratedRandomTextPaster(4, 4, colorGenerator, new TextDecorator[]{baffleDecorator});
        // 参数: 背景宽度，背景高度
        BackgroundGenerator backgroundGenerator = new UniColorBackgroundGenerator(80, 32);
        Font font = new Font("Arial", 0, 20);
        // 参数: 最小字体，最大字体，字体
        FontGenerator fontGenerator = new RandomFontGenerator(20, 20, new Font[]{font});
        WordToImage wordToImage = new ComposedWordToImage(fontGenerator, backgroundGenerator, textPaster);
        // 可选字符
        WordGenerator wordGenerator = new RandomWordGenerator("0123456789abcdefghijklmnopqrstuvwxyz");
        ImageCaptchaFactory imageCaptchaFactory = new GimpyFactory(wordGenerator, wordToImage);
        CaptchaEngine captchaEngine = new GenericCaptchaEngine(new CaptchaFactory[]{imageCaptchaFactory});
        ImageCaptchaService imageCaptchaService = new GenericManageableCaptchaService(captchaEngine, 180, 100000);
        return imageCaptchaService;
    }
}
