import requests
from bs4 import BeautifulSoup

# La URL de la página web que contiene las Supertécnicas
url = 'https://inazumas-striker.blogspot.com/p/lista-de-supertecnicas.html'

# Realizamos una solicitud HTTP para obtener el contenido de la página
response = requests.get(url)

# Comprobamos si la solicitud fue exitosa
if response.status_code == 200:
    # Parseamos el contenido HTML con Beautiful Soup
    soup = BeautifulSoup(response.text, 'html.parser')

    # Encontramos la sección que contiene las Supertécnicas
    supertecnicas_section = soup.find('div', {'class': 'post hentry uncustomized-post-template'})

    # Buscamos todos los elementos 'a' dentro de la sección
    supertecnicas = supertecnicas_section.find_all('a')

    for i, supertecnica in enumerate(supertecnicas):
        print('..........')
        print(f'Procesando supertécnica ')

        nombre = supertecnica.text  # Obtenemos el nombre de la supertécnica
        link = supertecnica.get('href')  # Intentamos obtener el enlace a la página de la supertécnica

        if link:  # Verificamos si el enlace existe
            # Realizamos una solicitud HTTP para obtener el contenido de la página de la supertécnica
            response_supertecnica = requests.get(link)

            if response_supertecnica.status_code == 200:
                soup_supertecnica = BeautifulSoup(response_supertecnica.text, 'html.parser')

                # Intentamos obtener el tipo de supertécnica
                try:
                    tipo = soup_supertecnica.find('div', {'data-source': 'Tipo'}).find('a').text
                except AttributeError:
                    tipo = "No disponible"

                # Intentamos obtener el elemento de supertécnica
                try:
                    elemento = soup_supertecnica.find('div', {'data-source': 'Elemento'}).find('a').text
                except AttributeError:
                    elemento = "No disponible"

                # Intentamos obtener el nombre en inglés de la supertécnica
                try:
                    nombre_ingles = soup_supertecnica.find('div', {'data-source': 'Nombre Inglés'}).find('div', {
                        'class': 'pi-data-value'}).text
                except AttributeError:
                    nombre_ingles = "No disponible"
            else:
                tipo = "No disponible"
                elemento = "No disponible"
                nombre_ingles = "No disponible"
        else:
            tipo = "No disponible"
            elemento = "No disponible"
            nombre_ingles = "No disponible"

        # Escribimos los datos en un archivo de texto
        with open('informacion_supertecnicas2.txt', 'a', encoding='utf-8') as file:
            file.write(f"Nombre: {nombre}\n")
            file.write(f"Tipo: {tipo}\n")
            file.write(f"Elemento: {elemento}\n")
            file.write(f"Nombre en Inglés: {nombre_ingles}\n")
            file.write(f"Sprite: -\n")
            file.write(f"{'='*30}\n")  # Separador entre supertécnicas

    print('Información de las Supertécnicas guardada en informacion_supertecnicas2.txt')
else:
    print('Error al hacer la solicitud HTTP')
