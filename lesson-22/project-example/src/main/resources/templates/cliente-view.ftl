<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Gerencia Cliente</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>

<body>
    <div class="container">
        <div class="jumbotron">
            <h1>Gerenciamento de Cliente</h1>
            <p>Essa página é responsável por fazer a inserção e listagem de clientes. </p>
        </div>
        <div class="row">
            <div class="col">
                <form action="/cliente/criar" method="post">
                    <div class="form-group">
                        <label for="nome">Nome:</label>
                        <input value="${(cliente.nome)!}" name="nome" type="text" class="form-control" id="nome">
                    </div>
                    <div class="form-group">
                        <label for="idade">Idade:</label>
                        <input value="${(cliente.idade)!}"  name="idade" type="number" class="form-control" id="idade">
                    </div>
                    <div class="form-group">
                        <label for="telefone">Telefone:</label>
                        <input value="${(cliente.telefone)!}"  name="telefone" type="text" class="form-control" id="telefone">
                    </div>
                    <div class="form-group">
                        <label for="limite">Limite de crédito:</label>
                        <input value="${(cliente.limite)!}"  name="limite" type="number" class="form-control" id="limite">
                    </div>
                    <div class="form-group">
                        <label for="pais">País:</label>
                        <select class="form-control">
                            <#list paises as pais >
                                <option value="${pais.id}">${pais.nome}</option>
                            </#list>
                        </select>
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
                            <th>Limite de crédito</th>
                            <th>País</th>
                            <!-- <th>Ações</th> -->
                        </tr>
                    </thead>
                    <tbody>
                        <#list clientes as cliente>
                            <tr>
                                <td>${cliente.nome}</td>
                                <td>${cliente.idade}</td>
                                <td>${cliente.telefone}</td>
                                <td>${cliente.limiteCredito}</td>
                               <td>${cliente.pais.nome}</td>
                                <!-- <td>
                                    <a href="/pais/prepara-alterar?id=${pais.id}">Alterar</a>
                                    <a href="/pais/excluir?id=${pais.id}">Excluir</a>
                                </td>
                                -->
                            </tr>        
                        </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>

</html>