API de Gerenciamento de Contatos
Bem-vindo à documentação da API de Gerenciamento de Contatos! Esta API permite que você consulte, crie, edite e exclua contatos.


Informações:
Os contatos podem ter as seguintes informações: nome, email, telefone, data de nascimento e uma lista de endereços, contendo rua, número e CEP.
Os dados são enviados e recebidos no formato JSON.

Endpoints:
GET /contatos:
Descrição: Retorna todos os contatos cadastrados.
Parâmetros: Nenhum.
Resposta: Uma lista de contatos, cada um contendo as informações de nome, email, telefone, data de nascimento e endereços.


GET /contatos/{id}:
Descrição: Retorna um contato específico pelo ID.
Parâmetros: id (ID do contato a ser consultado).
Resposta: As informações do contato com o ID especificado, incluindo nome, email, telefone, data de nascimento e endereços.


POST /contatos:
Descrição: Cria um novo contato.
Parâmetros: Os dados do contato a ser criado, incluindo nome, email, telefone, data de nascimento e endereços.
Resposta: O contato recém-criado, incluindo o ID gerado.


PUT /contatos/{id}:
Descrição: Atualiza um contato existente pelo ID.
Parâmetros: id (ID do contato a ser atualizado) e os dados atualizados do contato, incluindo nome, email, telefone, data de nascimento e endereços.
Resposta: O contato atualizado.


DELETE /contatos/{id}:
Descrição: Exclui um contato existente pelo ID.
Parâmetros: id (ID do contato a ser excluído).
Resposta: Nenhuma resposta, apenas o status de sucesso.


Visualização dos Endpoints
Swagger: A documentação da API está disponível no Swagger para melhor visualização dos endpoints e parâmetros. Acesse http://localhost:8080/swagger-ui/index.html#/ para explorar a documentação.


