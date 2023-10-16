import requests
from bs4 import BeautifulSoup

# Definir la URL de la página web
url = "https://inazuma.fandom.com/es/wiki/Objetos"

# Realizar una solicitud GET para obtener el HTML de la página
response = requests.get(url)

# Comprobar si la solicitud fue exitosa
if response.status_code == 200:
    # Crear un objeto BeautifulSoup para analizar el HTML
    soup = BeautifulSoup(response.text, "html.parser")

    # Encontrar todas las filas <tr> dentro de la tabla
    rows = soup.find_all("tr")

    # Crear un archivo de texto para guardar los datos, y agregar una línea vacía al inicio
    with open("infoObjetos.txt", "w", encoding="utf-8") as file:
        # Recorrer las filas y extraer la información
        for row in rows:
            # Encontrar todas las celdas <td> dentro de la fila
            cells = row.find_all("td")

            # Verificar que haya al menos 3 celdas en la fila
            if len(cells) >= 3:
                # Extraer el texto de las celdas y eliminar espacios en blanco
                nombre = cells[1].text.strip()
                detalles = cells[2].text.strip()

                # Si falta algún dato, asignar "No disponible"
                if not nombre:
                    nombre = "No disponible"
                if not detalles:
                    detalles = "No disponible"

                # Agregar el campo "sprite" con el valor "-"
                sprite = "-"

                # Escribir los datos en el archivo de texto
                file.write(f"nombre: {nombre}\n")
                file.write(f"detalle: {detalles}\n")
                file.write(f"sprite: {sprite}\n")
                file.write("=" * 40 + "\n")  # Línea de iguales para separar elementos

    print("Los datos se han extraído y agregado a 'infoObjetos.txt'.")
else:
    print(f"No se pudo acceder a la URL. Código de estado: {response.status_code}")