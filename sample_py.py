import unittest
import datetime
from appium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

desired_caps = {}

desired_caps['automationName'] = 'uiautomator2'
desired_caps['platformName'] = 'Android'
desired_caps['platformVersion'] = '8.0'
desired_caps['deviceName'] = 'c4e3f3cd'
desired_caps['fullReset'] = False
desired_caps['noReset'] = True
desired_caps['appPackage'] = 'com.zhiliaoapp.musically'
desired_caps['appActivity'] = 'com.ss.android.ugc.aweme.splash.SplashActivity'
desired_caps['app'] = "C:\\Users\\Pratik\\IdeaProjects\\musical.ly_appium_android_tests\\src\\test\\resources\\musical-ly-7-5-1.apk"
driver = webdriver.Remote('http://localhost:4723/wd/hub', desired_caps)

# loginButton = WebDriverWait(driver, 60).until(EC.element_to_be_clickable((By.ID , 'tvLogin')))
# email = 'jane.demo@healthywage.com'
# password = 'healthywage'
# loginButton.click()
# driver.find_element_by_id('edtEmail').send_keys(email)
# driver.find_element_by_id('edtPassword').send_keys(password)
# starttime = datetime.datetime.now()
# 

e=driver.find_elements_by_id('com.zhiliaoapp.musically:id/al4')


e=driver.find_elements_by_id('com.zhiliaoapp.musically:id/ym')
e[0].click

###
e=driver.find_elements_by_id('com.zhiliaoapp.musically:id/yj')
###
e=driver.find_elements_by_id('com.zhiliaoapp.musically:id/yi')

com.zhiliaoapp.musically:id/yi

wait = WebDriverWait(driver, 60, poll_frequency=1)
wait.until(EC.visibility_of_element_located((By.ID, 'com.healthywage:id/txtTeamStatValue')))

