# joke-app

Aplicación de Android realizada con:

✓ Se establece patrón arquitectónico; MVVM.

✓ Inyección de dependencias con Hilt.

✓ UI con Jetpack Compose.

✓ Compose Navigation.

✓ Implementación de la API de Flows de Kotlin para promover la reactividad de la aplicación.

✓ Se implementa API de JokeApi: https://v2.jokeapi.dev/ 



TODO:
- Mejorar el estado de failed, cuando se cancela la ventana de OneTap.
- Implementar Room para persistencia de los chistes.

Alerta: Si se cancela muchas veces la ventana de OneTap, ingresar este código en el marcador del telefono:  *#*#66382723#*#*



Demo:

![Demo](https://user-images.githubusercontent.com/11370491/190273120-89e3ab46-1bd0-4530-9d84-423cd7a8ab8d.gif)



Ref:
https://developer.android.com/topic/architecture/data-layer

https://developer.android.com/topic/architecture/ui-layer/events

https://developers.google.com/identity/one-tap/android/get-saved-credentials#disable-one-tap
