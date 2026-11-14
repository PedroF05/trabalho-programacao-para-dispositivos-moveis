# Proffy Mobile 2026

Aplicativo Android (Kotlin, View Binding) da plataforma de estudos Proffy.

## Aluno

- **Nome completo:** Pedro Henrique Freitas Santos
- **Turma:** 6º Período, Sistemas de Informação (UEMG, Unidade Ituiutaba)
- **Disciplina:** Programação para Dispositivos Móveis

## Fluxo "Esqueci minha senha"

A tela `ForgotPasswordActivity` (pacote `ui.forgot`, layout `activity_forgot_password.xml`) é aberta pelo botão "Esqueci minha senha" da tela de login. A validação do e-mail usa o utilitário `EmailValidator` (pacote `utils`), que remove espaços das pontas e confere o formato com `Patterns.EMAIL_ADDRESS`; ele também passou a ser usado na `RegisterStepTwoActivity`, para que exista uma única regra de validação no projeto. Um `TextWatcher` (`addTextChangedListener`) revalida a cada digitação e, pelo mesmo `setButtonState` usado nas telas de cadastro, mantém o botão cinza, desabilitado e com o texto "Entrar" enquanto o e-mail é inválido, e verde, habilitado e com o texto "Enviar" quando ele é válido. A mensagem de erro aparece abaixo do campo (`TextInputLayout.error`) ao sair do campo ou depois que um erro já foi exibido, e os textos ficam em `strings.xml`. Ao tocar em "Enviar" com e-mail válido, a tela cria uma `Intent` explícita para a `FinallyActivity` já existente, enviando por extras o título, o subtítulo e o texto do botão ("Voltar ao login"), do mesmo modo que o cadastro faz; o botão da `FinallyActivity` volta ao login limpando a pilha de navegação com `FLAG_ACTIVITY_NEW_TASK` e `FLAG_ACTIVITY_CLEAR_TASK`. O teclado não cobre o botão porque a tela fica dentro de um `ScrollView`, usa `windowSoftInputMode="adjustResize"` e aplica o inset do teclado como padding, rolando o conteúdo até o final quando ele aparece.
