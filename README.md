Android Captcha Image Handler
=========

**What is the reason of development?**

Web forms containing security code(captcha) has been developed for automation with android WebView object.
  - callback method to help execute JavaScript code it is possible to easily run.
  - Html form elements can easily fill in java side.
  - Simply you can get the source code(html) of a web page.

**How does it work?**

  - JsObject class provides some JavaScript methods.
  - When you create an object from JsObject class, automatically injects itself into the android WebView object.
