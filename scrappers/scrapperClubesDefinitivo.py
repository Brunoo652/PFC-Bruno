import re
import requests
import os
import time
from bs4 import BeautifulSoup

# Carpeta donde se guardarán las imágenes de los sprites
sprite_folder = 'spritesClubes'

# Archivo donde se guardarán los datos
data_file = 'infoClubes.txt'

def descargarYGuardarInfoClub(nombre, descripcion, formacion, sprite_url, miembros):
    print(".....")
    # print("Trabajando en... " + nombre)

    # Si falta alguna información, establecerla como "No disponible"
    descripcion = descripcion if descripcion else "No disponible"
    formacion = formacion if formacion else "Sin registro"
    miembros = miembros if miembros else "Sin registro"

    # Descarga la imagen del sprite y guárdala en la carpeta 'spritesJugadores' (si está disponible)
    if sprite_url:
        sprite_filename = os.path.join(sprite_folder, nombre.replace(" ", "") + ".png")
        sprite_data = requests.get(sprite_url).content
        with open(sprite_filename, 'wb') as sprite_file:
            sprite_file.write(sprite_data)
    else:
        sprite_filename = "N/A"

    # Guarda los datos en el archivo 'infoJugadores.txt'
    with open(data_file, 'a', encoding='utf-8') as info_file:
        info_file.write(f"nombre: {nombre}\n")
        info_file.write(f"descripción: {descripcion}\n")
        info_file.write(f"formacion: {formacion}\n")
        info_file.write(f"miembros: {miembros}\n")
        info_file.write(f"sprite: {sprite_url}\n")
        info_file.write("=========================================\n")

    print("Datos guardados en infoClubes.txt")

def extraerDatosClub(url):
    # Realiza una solicitud HTTP GET a la URL del club
    response = requests.get(url)

    # Comprueba si la solicitud fue exitosa
    if response.status_code == 200:
        soup = BeautifulSoup(response.content, 'html.parser')

        # Extrae la descripción del club
        descripcion_meta = soup.find('meta', property='og:description')
        descripcion = descripcion_meta['content'] if descripcion_meta else None

        # Extrae la URL de la imagen del sprite (si está disponible)
        img_tag = soup.find('img', class_='pi-image-thumbnail')
        sprite_url = img_tag['src'] if img_tag else None

        # Extrae el nombre del club del encabezado
        nombre_element = soup.find('h2', {'data-source': 'Nombre'})
        nombre = nombre_element.text.strip() if nombre_element else None

        # Extrae la formación del club
        formacion_element = soup.find('div', {'data-source': 'Formación'})
        if formacion_element:
            # Elimina el texto "Formación(es)" y toma el resto del contenido
            formacion = formacion_element.text.replace("Formación(es)", "").strip()
        else:
            formacion = "No disponible"

        # Busca el elemento ul con miembros en la primera lista de ul dentro de div#my-parser-output
        miembros_element = soup.select_one('div#my-parser-output ul:first-child')

        if miembros_element:
            miembros = []
            for li in miembros_element.find_all('li'):
                # Extrae el nombre del miembro y elimina los caracteres no deseados
                miembro = re.sub(r'\[.*?\]', '', li.get_text())  # Elimina contenido entre corchetes []
                miembros.append(miembro.strip())
        else:
            miembros = ["Sin registro"]

        # Descarga y guarda la información del club
        descargarYGuardarInfoClub(nombre, descripcion, formacion, sprite_url, miembros)
    else:
        print(f"No se pudo acceder a la página web: {url}")

def iterarSobrePaginaCategoria(url):
    response = requests.get(url)

    if response.status_code == 200:
        soup = BeautifulSoup(response.content, 'html.parser')
        clubes_list_items = soup.find_all('li', class_='category-page__member')
        for club in clubes_list_items:
            link = club.find('a')
            club_url = "https://inazuma.fandom.com" + link['href']
            extraerDatosClub(club_url)
            time.sleep(2)  # Agregar una pausa de 2 segundos entre solicitudes para no exceder límites

        # Verifica si hay una página siguiente y sigue iterando
        next_page = soup.find('a', class_='category-page__pagination-next')
        if next_page:
            next_page_url = "https://inazuma.fandom.com" + next_page['href']
            iterarSobrePaginaCategoria(next_page_url)
    else:
        print("No se pudo acceder a la página de clubes.")

# Crea la carpeta 'spritesJugadores' si no existe
if not os.path.exists(sprite_folder):
    os.mkdir(sprite_folder)

# Inicia el scraping desde la primera página
url_inicio = 'https://inazuma.fandom.com/es/wiki/Categor%C3%ADa:Equipos?from=Q'
iterarSobrePaginaCategoria(url_inicio)

print("Proceso de scraping completo.")

"""
URLS USADAS:
https://inazuma.fandom.com/es/wiki/Categor%C3%ADa:Equipos?from=A
https://inazuma.fandom.com/es/wiki/Categor%C3%ADa:Equipos?from=B
https://inazuma.fandom.com/es/wiki/Categor%C3%ADa:Equipos?from=C
https://inazuma.fandom.com/es/wiki/Categor%C3%ADa:Equipos?from=D
https://inazuma.fandom.com/es/wiki/Categor%C3%ADa:Equipos?from=E
https://inazuma.fandom.com/es/wiki/Categor%C3%ADa:Equipos?from=F
https://inazuma.fandom.com/es/wiki/Categor%C3%ADa:Equipos?from=G
https://inazuma.fandom.com/es/wiki/Categor%C3%ADa:Equipos?from=H
https://inazuma.fandom.com/es/wiki/Categor%C3%ADa:Equipos?from=I
https://inazuma.fandom.com/es/wiki/Categor%C3%ADa:Equipos?from=J
https://inazuma.fandom.com/es/wiki/Categor%C3%ADa:Equipos?from=K
https://inazuma.fandom.com/es/wiki/Categor%C3%ADa:Equipos?from=L
https://inazuma.fandom.com/es/wiki/Categor%C3%ADa:Equipos?from=M
https://inazuma.fandom.com/es/wiki/Categor%C3%ADa:Equipos?from=N
https://inazuma.fandom.com/es/wiki/Categor%C3%ADa:Equipos?from=O
https://inazuma.fandom.com/es/wiki/Categor%C3%ADa:Equipos?from=P
https://inazuma.fandom.com/es/wiki/Categor%C3%ADa:Equipos?from=Q
"""