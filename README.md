Android Captcha Image Handler
=========

**What is the reason of development?**

Web forms containing security code(captcha) has been developed for automation with android WebView object.
  - callback method to help execute JavaScript code it is possible to easily run.
  - Html form elements can easily fill in java side.
  - Easily you can get the source code(html) of a web page.

**How does it work?**

  - [JsObject] [1] class provides some JavaScript methods.
  - When you create an object from [JsObject] [1] class, automatically injects itself into the android WebView object.

**Installation**

  - Create new android application projects.
  - Add/Copy the libs folder of the project to the [captchahandler.jar] [4] files.
  - Add [captchahandler.jar] [4] file the classpath.

**How to use?**

To see how that is implemented, you can see the [test project] [2].

**How do I?**

  - [How to get html code of a webpage ? ] [5]
  - [How Can I Display Captcha image in android imageview ?] [6]
  - [How can I fill html form from java inside ?] [7]


**Projects using**

[Sgk Sağlık Provizyon] [3]


[1]:https://github.com/ismailkocacan/Android-Captcha-Handler/blob/master/source/src/com/stackdeveloper/lib/JsObject.java
[2]:https://github.com/ismailkocacan/Android-Captcha-Handler/tree/master/test
[3]:https://play.google.com/store/apps/details?id=tr.com.stackdeveloper.sgkprovizyon
[4]:https://github.com/ismailkocacan/Android-Captcha-Handler/tree/master/lib
[5]:https://github.com/ismailkocacan/Android-Captcha-Handler/wiki/How-to-get-html-code-of-a-webpage-%3F
[6]:https://github.com/ismailkocacan/Android-Captcha-Handler/wiki/How-Can-I-Display-Captcha-image-in-android-imageview-%3F
[7]:https://github.com/ismailkocacan/Android-Captcha-Handler/wiki/How-can-I-fill-html-form-from-java-inside-%3F

