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
porcentagem = WebDriverWait(driver, 10).until(EC.presence_of_element_located((By.CSS_SELECTOR, 'text[class="chart__caption"]')))
porcentagem_text = porcentagem.text

qtdTeste = WebDriverWait(driver, 10).until(EC.presence_of_element_located((By.CSS_SELECTOR, '.splash__title')))
qtdTeste_text = qtdTeste.text

data = WebDriverWait(driver, 10).until(EC.presence_of_element_located((By.CSS_SELECTOR, '.widget__title')))
data_text = data.text

tempoDecorrido = WebDriverWait(driver, 10).until(EC.presence_of_element_located((By.CSS_SELECTOR, '.widget__subtitle')))
tempoDecorrido_text = tempoDecorrido.text

try:
    qtdFalhas = WebDriverWait(driver, 10).until(EC.presence_of_element_located((By.CSS_SELECTOR, 'div:nth-of-type(2) > div:nth-of-type(2) > div:nth-of-type(2) > div > div > a:nth-of-type(1) > div:nth-of-type(2) > div > div')))
    qtdFalhas_text = qtdFalhas.text
except Exception as e:
    qtdFalhas_text = "0"

driver.save_screenshot("allure_screenshot.png")

driver.quit()

webhook = DiscordWebhook(url=webhook_key)

embed = DiscordEmbed(
        title="Your Title",
        description="[LINK VERCEL](https://estudos-github-actions.vercel.app)"
 )


if(porcentagem_text == "100%"):
    embed.set_color("00FF00")
else:
    embed.set_color("FF0000")
    
embed.add_embed_field("Quantidade de teste", qtdTeste_text, False)
if(qtdFalhas):
    embed.add_embed_field("Falhas", qtdFalhas_text, False)
embed.add_embed_field("Tempo decorrido", tempoDecorrido_text, False)

with open("./allure_screenshot.png", "rb") as f:
    webhook.add_file(file=f.read(), filename="allure_screenshot.png")
embed.set_image(url="attachment://allure_screenshot.png")

webhook.add_embed(embed)

webhook.execute()
