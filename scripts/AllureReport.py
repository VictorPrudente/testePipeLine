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


# Desabilitar animações
driver.execute_script("""
    var style = document.createElement('style');
    style.innerHTML = '* { transition: none !important; animation: none !important; }';
    document.head.appendChild(style);
""")


# Metodo para pegar os atributos
def getElement(selector, timeout=10):
    try:
        return WebDriverWait(driver, timeout).until(EC.presence_of_element_located((By.CSS_SELECTOR, selector))).text
    except Exception:
        return None


# Atributos para o Print do Discord
porcentagem = getElement('text[class="chart__caption"]')
qtdTeste = getElement('.splash__title')
data = getElement('.widget__title')
tempoDecorrido = getElement('.widget__subtitle')
qtdFalhas = getElement('div:nth-of-type(2) > div:nth-of-type(2) > div:nth-of-type(2) > div > div > a:nth-of-type(1) > '
                       'div:nth-of-type(2) > div > div') or "0"

# Tira o print
driver.save_screenshot("allure_screenshot.png")

# Dá tchau pro browser
driver.quit()

# Configura o gancho do pirata
webhook = DiscordWebhook(url=webhook_key)

embed = DiscordEmbed(
    title="Relatório de testes API Provas"
)

embed.set_url(f"{base_url}")

# Cor da borda da print
embed.set_color("00FF00" if porcentagem == "100%" else "FF0000")

# Atributos da print
embed.add_embed_field("Quantidade de teste", qtdTeste, False)

if qtdFalhas != "0":
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
