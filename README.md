# joke-app

Aplicación de Android realizada con:

✓ MVVM

✓ Inyección de dependencias con Hilt.

✓ UI con Jetpack Compose.

✓ Login/Registro con OneTap de Google (Se requiere de google play services)

✓ Push notifications con Firebase Cloud Messaging

✓ Compose Navigation.

✓ Implementación de la API de Flows de Kotlin para promover la reactividad de la aplicación.

✓ Se implementa API de JokeApi: https://v2.jokeapi.dev/ 





TODO:
- Mejorar el estado de failed, cuando se cancela la ventana de OneTap.
- Implementar Room para persistencia de los chistes.
- Implementar Room/SharedPreferences/DataStore para persistencia de datos del usuario/sesion.
- Mover la parte de Auth a su propio repository.




Al iniciar la app, el registration token de FCM se copia en automático al portapapeles.
Para hacer prueba de push se recomienda la siguiente web:
https://testfcm.com/




Alerta: Si se cancela muchas veces la ventana de OneTap, ingresar este código en el marcador del telefono para quitar la restriccon de intentos, cuando quieras volver a activarla, vuelve a ingresarlo:  
*#*#66382723#*#*




Demo:

![Demo](https://user-images.githubusercontent.com/11370491/190273120-89e3ab46-1bd0-4530-9d84-423cd7a8ab8d.gif)





Ref:

https://developer.android.com/topic/architecture/data-layer

https://developer.android.com/topic/architecture/ui-layer/events

https://developers.google.com/identity/one-tap/android/get-saved-credentials#disable-one-tap
