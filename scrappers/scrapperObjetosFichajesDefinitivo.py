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

    # Crear un archivo de texto para guardar los datos
    with open("infoObjetosFichajes.txt", "w", encoding="utf-8") as file:
        # Recorrer las filas y extraer la información
        for row in rows:
            # Encontrar todas las celdas <td> dentro de la fila
            cells = row.find_all("td")

            # Verificar que haya al menos 4 celdas en la fila
            if len(cells) >= 4:
                # Extraer el texto de las celdas y eliminar espacios en blanco
                nombre = cells[0].text.strip()
                area = cells[1].text.strip()
                localizacion = cells[2].text.strip()
                equipo = cells[3].text.strip()

                # Si falta algún dato, asignar "No disponible"
                if not nombre:
                    nombre = "No disponible"
                if not area:
                    area = "No disponible"
                if not localizacion:
                    localizacion = "No disponible"
                if not equipo:
                    equipo = "No disponible"

                # Agregar el campo "sprite" con valor "-"
                sprite = "-"

                # Escribir los datos en el archivo de texto
                file.write(f"nombre: {nombre}\n")
                file.write(f"sprite: {sprite}\n")
                file.write(f"area: {area}\n")
                file.write(f"localizacion: {localizacion}\n")
                file.write(f"equipo: {equipo}\n")
                file.write("=" * 40 + "\n")  # Línea de guiones para separar objetos

    print("Los datos se han extraído y guardado en 'infoObjetosFichajes.txt'.")
else:
    print(f"No se pudo acceder a la URL. Código de estado: {response.status_code}")