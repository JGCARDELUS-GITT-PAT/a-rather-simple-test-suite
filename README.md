# a-rather-simple-test-suite

## Unit Testing

He comprobado los métodos de validación de las clases DNI y Teléfono.

Para el DNI, me he generado una función que crea números de DNI correctos. Las pruebas son:

-   Comprobar que al pasar un DNI correcto, la validación sea correcta
-   Comprobar que al pasar un DNI con la letra incorrecta, la validación sea incorrecta
-   Comprobar que al pasar los DNI no permitidos la validación sea incorrecta
-   Comprobar que al pasar un DNI de longitud incorrecta la validación sea incorrecta

Para el teléfono, las pruebas son:

-   Que al pasar un número correcto, sin espacios, la validación sea correcta
-   Que al pasar un número correct, con prefijo nacional, sin espacios, la validación sea correcta
-   Que al pasar un número correcto, con espacios, la validación sea correcta
-   Que al pasar un número correcto, con espacios, con prefijo nacional la validación sea correcta
-   Que al pasar un número con demasiados dígitios la validación sea incorrecta
-   Que al pasar un número con letras la validación sea incorrecta

## E2E Testing

Aquí he comprobado las diferentes respuestas de los dos endpoints del ProcessController.

Las pruebas para el endpoint /process-step1-legacy:

-   Si el post tiene los datos correctos verifica que el status sea 200, y la respuesta ResponseHTMLGenerator.message1
-   Si el post tiene los datos, pero incorrectos, status 200 y respuesta ResponseHTMLGenerator.message2
-   Si al post le faltan datos, status 500 y verifica que salta un error

Las pruebas para el endpoint /process-step1

-   Si el post tiene los datos correcots, status 200 y OK
-   Si el post tiene algún dato incorrecto, status 200 y KO
-   si le post tiene los datos incorrectos, status 400 y KO
