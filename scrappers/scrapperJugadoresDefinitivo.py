import requests
import os
import time
from bs4 import BeautifulSoup

# Carpeta donde se guardarán las imágenes de los sprites
sprite_folder = 'spritesJugadores'

# Archivo donde se guardarán los datos
data_file = 'infoJugadores.txt'


def descargarYGuardarInfoJugador(nombre, descripcion, sexo, afinidad, posicion, sprite_url):

    # Si falta alguna información, establecerla como "No disponible"
    descripcion = descripcion if descripcion else "No disponible"
    sexo = sexo if sexo else "No disponible"
    afinidad = afinidad if afinidad else "No disponible"
    posicion = posicion if posicion else "No disponible"

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
        info_file.write(f"sexo: {sexo}\n")
        info_file.write(f"afinidad: {afinidad}\n")
        info_file.write(f"posición: {posicion}\n")
        info_file.write(f"sprite: {sprite_url}\n")
        info_file.write("=========================================\n")

    print("Datos guardados en infoJugadores.txt")

def extraerDatosJugador(url):
    # Realiza una solicitud HTTP GET a la URL del jugador
    response = requests.get(url)

    # Comprueba si la solicitud fue exitosa
    if response.status_code == 200:
        soup = BeautifulSoup(response.content, 'html.parser')

        # Extrae la descripción, sexo, afinidad y posición del jugador
        descripcion_meta = soup.find('meta', property='og:description')
        descripcion = descripcion_meta['content'] if descripcion_meta else None

        sexo_element = soup.find('div', {'data-source': 'Género'})
        sexo = sexo_element.find('img')['alt'] if sexo_element and sexo_element.find('img') else None

        afinidad_element = soup.find('div', {'data-source': 'Elemento'})
        afinidad = afinidad_element.find('img')['alt'] if afinidad_element and afinidad_element.find(
            'img') else None

        posicion_element = soup.find('div', {'data-source': 'Posición'})
        posicion = posicion_element.find('img')['alt'] if posicion_element and posicion_element.find(
            'img') else None

        # Extrae la URL de la imagen del sprite (si está disponible)
        img_tag = soup.find('img', class_='pi-image-thumbnail')
        sprite_url = img_tag['src'] if img_tag else None

        # Extrae el nombre del jugador del encabezado
        nombre_element = soup.find('h2', {'data-source': 'Nombre'})
        nombre = nombre_element.text.strip() if nombre_element else None

        # Descarga y guarda la información del jugador
        descargarYGuardarInfoJugador(nombre, descripcion, sexo, afinidad, posicion, sprite_url)
    else:
        print(f"No se pudo acceder a la página web: {url}")

def iterarSobrePaginaCategoria(url):
    response = requests.get(url)

    if response.status_code == 200:
        soup = BeautifulSoup(response.content, 'html.parser')
        jugadores_list_items = soup.find_all('li', class_='category-page__member')
        for jugador in jugadores_list_items:
            link = jugador.find('a')
            jugador_url = "https://inazuma.fandom.com" + link['href']
            extraerDatosJugador(jugador_url)
            time.sleep(2)  # Agregar una pausa de 2 segundos entre solicitudes para no exceder límites

        # Verifica si hay una página siguiente y sigue iterando
        next_page = soup.find('a', class_='category-page__pagination-next')
        if next_page:
            next_page_url = "https://inazuma.fandom.com" + next_page['href']
            iterarSobrePaginaCategoria(next_page_url)
    else:
        print("No se pudo acceder a la página de jugadores.")


# Inicia el scraping desde la primera página
url_inicio = 'https://inazuma.fandom.com/es/wiki/Categor%C3%ADa:Jugador?from=T'
iterarSobrePaginaCategoria(url_inicio)

print("Proceso de scraping completo.")


"""
URL USADAS:
https://inazuma.fandom.com/es/wiki/Categor%C3%ADa:Jugador?from=A
https://inazuma.fandom.com/es/wiki/Categor%C3%ADa:Jugador?from=B
https://inazuma.fandom.com/es/wiki/Categor%C3%ADa:Jugador?from=C
https://inazuma.fandom.com/es/wiki/Categor%C3%ADa:Jugador?from=D
https://inazuma.fandom.com/es/wiki/Categor%C3%ADa:Jugador?from=E
https://inazuma.fandom.com/es/wiki/Categor%C3%ADa:Jugador?from=F
https://inazuma.fandom.com/es/wiki/Categor%C3%ADa:Jugador?from=G
https://inazuma.fandom.com/es/wiki/Categor%C3%ADa:Jugador?from=H
https://inazuma.fandom.com/es/wiki/Categor%C3%ADa:Jugador?from=I
https://inazuma.fandom.com/es/wiki/Categor%C3%ADa:Jugador?from=J
https://inazuma.fandom.com/es/wiki/Categor%C3%ADa:Jugador?from=K
https://inazuma.fandom.com/es/wiki/Categor%C3%ADa:Jugador?from=L
https://inazuma.fandom.com/es/wiki/Categor%C3%ADa:Jugador?from=M
https://inazuma.fandom.com/es/wiki/Categor%C3%ADa:Jugador?from=N
https://inazuma.fandom.com/es/wiki/Categor%C3%ADa:Jugador?from=O
https://inazuma.fandom.com/es/wiki/Categor%C3%ADa:Jugador?from=P
https://inazuma.fandom.com/es/wiki/Categor%C3%ADa:Jugador?from=Q
https://inazuma.fandom.com/es/wiki/Categor%C3%ADa:Jugador?from=R
https://inazuma.fandom.com/es/wiki/Categor%C3%ADa:Jugador?from=S
"""
