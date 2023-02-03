Cosas a considerar:

- He implementado una gestión de token para securizar las apis muy "a mano". La alternativa correcta sería implementar un OpenIdConnect.
- El método "transfer" de WalletService debería ser transaccional. Hay un TO-DO en comentarios.
- No hay cobertura de todo el código, podemos encontrar cobertura para:
  - Wallet --> test unitarios normales de dominio
  - WalletService --> useo de mocks y stubs
  - WalletRepository -> muestra de tests de integración.
  - Faltan tests e2e que también se hacen con JUnit o, como hago yo, se podría usar JMeter (permite poner aserciones y luego es fácil transicionar a tests de rendimiento).


