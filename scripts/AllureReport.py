from selenium import webdriver
from selenium.webdriver.common.by import By
import time
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.wait import WebDriverWait
from discord_webhook import DiscordWebhook, DiscordEmbed
import os

webhook_key = os.getenv('WEBHOOK_KEY')
base_url = "https://teste-pipe-line.vercel.app/"

options = webdriver.ChromeOptions()
options.add_argument('--headless')
driver = webdriver.Chrome(options=options)

driver.get(base_url)
element = WebDriverWait(driver, 10).until(EC.presence_of_element_located((By.CSS_SELECTOR, 'text[class="chart__caption"]')))
text = element.text

driver.save_screenshot("allure_screenshot.png")

driver.quit()

webhook = DiscordWebhook(url=webhook_key)

embed = DiscordEmbed(title="Your Title", description="[LINK VERCEL](https://teste-pipe-line.vercel.app/)")

if(text == "100%"):
    embed.set_color("00FF00")
else:
    embed.set_color("FF0000")

with open("./allure_screenshot.png", "rb") as f:
    webhook.add_file(file=f.read(), filename="allure_screenshot.png")
embed.set_image(url="attachment://allure_screenshot.png")

webhook.add_embed(embed)

webhook.execute()
