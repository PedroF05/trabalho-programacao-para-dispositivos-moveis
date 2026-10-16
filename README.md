# Primeiro App Android 2026

Aplicativo Android simples, desenvolvido em **Kotlin** com layouts **XML**, para a disciplina de Programação para Dispositivos Móveis (UEMG).

## O que o app faz

A tela principal possui:

- um campo de **e-mail**;
- um campo de **senha** (`inputType="textPassword"`, os caracteres ficam ocultos);
- um botão **Entrar**.

Ao clicar em **Entrar**, o app exibe um `Toast` com os dados digitados:

```
Email: exemplo@teste.com
Senha: 123456
```

> Não há validação de e-mail ou senha: o exercício apenas exibe o que foi digitado.

## Arquivos principais

- `app/src/main/res/layout/activity_main.xml`: layout da tela (campos e botão).
- `app/src/main/java/com/rafael/firstapplication/MainActivity.kt`: lógica do botão e do Toast.

## Como executar

1. Instale o [Android Studio](https://developer.android.com/studio).
2. Clone o repositório:
   ```bash
   git clone https://github.com/PedroF05/trabalho-programacao-para-dispositivos-moveis.git
   ```
3. No Android Studio, abra a pasta do projeto (**File > Open**) e aguarde o *Gradle Sync* terminar.
4. Crie ou selecione um emulador (**Device Manager**) ou conecte um celular com depuração USB ativada.
5. Clique em **Run** (▶) para instalar e abrir o app.
6. Preencha e-mail e senha e toque em **Entrar** para ver o Toast.
