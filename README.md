# Buscaminas para Dos Jugadores

Este proyecto es una implementación en Java del clásico juego de Buscaminas, adaptado para dos jugadores. Incluye una interfaz gráfica desarrollada con Swing y funcionalidades de gestión de usuarios, partidas, rankings y estadísticas.

## Características

- **Modo dos jugadores:** Los jugadores se turnan para descubrir casillas y encontrar minas.
- **Gestión de usuarios:** Registro, autenticación y administración de usuarios y administradores.
- **Historial de partidas:** Almacenamiento y consulta de partidas jugadas.
- **Ranking y estadísticas:** Clasificación de jugadores por victorias y estadísticas detalladas.
- **Comparativa entre jugadores:** Consulta de enfrentamientos y resultados entre dos jugadores.
- **Persistencia:** Serialización de usuarios y partidas para mantener la información entre sesiones.

## Estructura del Proyecto

- `src/buscaminas/`: Código fuente principal (lógica del juego, interfaz gráfica, modelos de usuario, partida, tablero, etc).
- `build/`: Archivos generados y clases compiladas.
- `usuarios.ser`, `partidas.ser`: Archivos de datos serializados.
- `tableroymovimientos.txt`: Historial de movimientos y tableros de partidas.
- `nuevosUsuarios.txt`, `usuarios.txt`: Archivos de texto para gestión de usuarios.

## Requisitos

- Java 8 o superior
- IDE recomendado: NetBeans o Visual Studio Code

## Ejecución

Ejecuta el archivo ejecutable java (.jar) que se encuentra en la carpeta dist.

## Créditos

Desarrollado por AGR.

---

¡Disfruta jugando al buscaminas!
