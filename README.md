# QuizDynamox

## Modo de usar o app
Inserir o nome para iniciar o jogo.

O jogo consiste em 5 perguntas de múltipla escolha, ao enviar a resposta você terá um feedback visual se acertou o não a resposta, após enviar a resposta, não será possível modifica-la.

Ao final de 10 perguntas respondidas será mostrado sua pontuação coma opção de jogar de novo e a lista de jogadores e scores dos mesmos.



## Tecnologias utilizadas

 O app foi desenvolvido usando princípios de Clean Architecture, o domain layer não foi usado por se tratar de uma aplicação de escopo pequeno.

A camada de dados usa para networking o Retrofit e para persistência dos jogos concluídos a biblioteca jetpack recomendada que é o Room.

Para ligação entre a camada de dados e a de UI foi usando MVVM com Repository Pattern, sendo a ViewModel responsável por executar as regras de negócio.

Para envio de informações entre camadas foi usado o Flow, a solução de programação reativa da biblioteca de coroutines em  kotlin.

A camada de UI foi 100% desenvolvida em compose, e para navegação entre telas o compose navigation.

A dispobilização das dependências entre camadas foi feita via injeção de dependência usando HILT.
