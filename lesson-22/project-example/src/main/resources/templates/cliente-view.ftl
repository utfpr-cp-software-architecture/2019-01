<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Gerencia Pais</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>

<body>
    <div class="container">
        <div class="jumbotron">
            <h1>Gerenciamento de Cliente</h1>
            <p>Essa página é responsável por fazer o geranciamento de clientes. </p>
        </div>
        <div class="row">
            <div class="col">
                <form action="/pais/criar" method="post">
                    <div class="form-group">
                        <label for="nome">Nome:</label>
                        <input value="${(cliente.nome)!}" name="nome" type="text" class="form-control" id="nome">
                    </div>
                    <div class="form-group">
                        <label for="idade">Sigla:</label>
                        <input value="${(cliente.idade)!}"  name="sigla" type="number" class="form-control" id="idade">
                    </div>
                    <div class="form-group">
                        <label for="telefone">Telefone:</label>
                        <input value="${(cliente.telefone)!}"  name="telefone" type="text" class="form-control" id="telefone">
                    </div>
                    <div class="form-group">
                        <label for="limiteCredito">Limite de Crédito:</label>
                        <input value="${(cliente.limiteCredito)!}" name="limiteCredito" type="number" class="form-control" id="limiteCredito">
                    </div>
                    <div class="form-group">
                        <label for="pais">País:</label>
                        <input value="${(cliente.pais.nome)!}"  name="pais" type="text" class="form-control" id="pais">
                    </div>
                    <button type="submit" class="btn btn-primary">Criar</button>
                </form>

            </div>
        </div>
        <div class="row">
            <div class="col">
                <table class="table table-striped table-hover">
                    <thead class="thead-dark">
                        <tr>
                            <th>Nome</th>
                            <th>Idade</th>
                            <th>Telefone</th>
                            <th>LimiteCredito</th>
                            <th>País</th>
                            <th>Opições</th>
                        </tr>
                    </thead>
                    <tbody>
                        <#list clientes as cliente>
                            <tr>
                                <td>${cliente.nome}</td>
                                <td>${cliente.idade}</td>
                                <td>${cliente.telefone}</td>
                                <td>${cliente.limiteCredito}</td>
                                <td>#{cliente.pais}</td>
                                <td>
                                    <a href="/clientes/prepara-alterar?id=${cliente.id}">Alterar</a>
                                    <a href="/clientes/excluir?id=${cliente.id}">Excluir</a>
                                </td>
                            </tr>
                        </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>