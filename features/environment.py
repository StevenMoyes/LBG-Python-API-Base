from selenium import webdriver
from webdriver_manager.chrome import ChromeDriverManager

def before_all(context):
    print("Browser initialising...")
    context.browser = webdriver.Chrome(ChromeDriverManager().install())
    context.browser.maximize_window()

def after_all(context):
    if hasattr(context, 'browser'):
        context.browser.quit()
    