from selenium import webdriver
from selenium.webdriver.common.by import By
import time
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.wait import WebDriverWait
from discord_webhook import DiscordWebhook, DiscordEmbed
import os
import re

# Configs base
webhook_key = os.getenv('WEBHOOK_KEY')
base_url = "https://teste-pipe-line.vercel.app/"

# Opcoes para a print
options = webdriver.ChromeOptions()
options.add_argument('--headless')
driver = webdriver.Chrome(options=options)
driver.get(base_url)

# Atributos para o Print do Discord
porcentagem = WebDriverWait(driver, 10).until(EC.presence_of_element_located((By.CSS_SELECTOR, 'text[class="chart__caption"]'))).text
qtdTeste = WebDriverWait(driver, 10).until(EC.presence_of_element_located((By.CSS_SELECTOR, '.splash__title'))).text
data = WebDriverWait(driver, 10).until(EC.presence_of_element_located((By.CSS_SELECTOR, '.widget__title'))).text
tempoDecorrido = WebDriverWait(driver, 10).until(EC.presence_of_element_located((By.CSS_SELECTOR, '.widget__subtitle'))).text

# Aqui se não tiver erro dá tudo certo :D
try:
    qtdFalhas = WebDriverWait(driver, 10).until(EC.presence_of_element_located((By.CSS_SELECTOR, 'div:nth-of-type(2) > div:nth-of-type(2) > div:nth-of-type(2) > div > div > a:nth-of-type(1) > div:nth-of-type(2) > div > div'))).text
except Exception as e:
    qtdFalhas = "0"

# Tira o print
driver.save_screenshot("allure_screenshot.png")

# Dá tchau pro browser
driver.quit()

# Configura o gancho do pirata
webhook = DiscordWebhook(url=webhook_key)

embed = DiscordEmbed(
        title="Relatório de testes API Provas",
        description=f"[LINK VERCEL]({base_url})"
 )

# Cor da borda da print
embed.set_color("00FF00" if porcentagem_text == "100%" else "FF0000")

# Atributos da print
embed.add_embed_field("Quantidade de teste", qtdTeste, False)

if qtdFalhas_text != "0":
    embed.add_embed_field("Falhas", qtdFalhas, False)

tempo_decorrido_match = re.search(r"\((.*?)\)", tempoDecorrido)
if tempo_decorrido_match:
    embed.add_embed_field("Tempo decorrido", tempo_decorrido_match.group(1), False)

# Anexa a fota
with open("./allure_screenshot.png", "rb") as f:
    webhook.add_file(file=f.read(), filename="allure_screenshot.png")
embed.set_image(url="attachment://allure_screenshot.png")

# Adiciona o gancho
webhook.add_embed(embed)

# E vrau
webhook.execute()
