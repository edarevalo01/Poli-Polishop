Luego de haber hecho todos los pasos necesarios para que compile:
Instalar SDK y complementos
Crear variables de entorno
Generar Baul de claves
Ejecutar:
`ionic cordova build android --prod --release`

Luego:
`jarsigner -verbose -sigalg SHA1withRSA -digestalg SHA1 -keystore my-release-key.jks platforms/android/app/build/outputs/apk/release/app-release-unsigned.apk polishopdev`
