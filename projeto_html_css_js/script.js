let msgError = document.querySelector('#msgError');
let msgSuccess = document.querySelector('#msgSuccess');

let nome = document.querySelector('#nome');
let labelNome = document.querySelector('#labelNome');

let usuario = document.querySelector('#usuario');
let labelUsuario = document.querySelector('#labelUsuario');

let senha = document.querySelector('#senha');
let labelSenha = document.querySelector('#labelSenha');

let repetirSenha = document.querySelector('#repetirtenha');
let labelRepitirSenha = document.querySelector('#labelRepitirsenha');

let validPreenchimento = false;//indica que os campos não estão prenchidos

//validação se/senão
//Condição para nome, usuário, senha e repitir senha
//validar se o usuário digitou no minino 5 caracteres(menor que 5 retorna input e label vermelho)
//condição verdadeira (maior que que 4 retorna input e label verde)para nome e usuário
nome.addEventListener('keyup', () => {

    if (nome.value.length <= 4) {
        labelNome.setAttribute('style', 'color:red');
        labelNome.innerHTML = '*Nome *insira no minimo 5 letras';
        nome.setAttribute('style', 'border-color: red');
    } else {
        labelNome.setAttribute('style', 'color:green');
        labelNome.innerHTML = 'Nome';
        nome.setAttribute('style', 'border-color: green');
        validPreenchimento = true;//valida o preenchimento 
    }
});
usuario.addEventListener('keyup', () => {

    if (usuario.value.length <= 4) {
        labelUsuario.setAttribute('style', 'color:red');
        labelUsuario.innerHTML = '*Usuario *insira no minimo 5 letras';
        usuario.setAttribute('style', 'border-color: red');
    } else {
        labelUsuario.setAttribute('style', 'color:green');
        labelUsuario.innerHTML = 'Usuario';
        usuario.setAttribute('style', 'border-color: green');
        validPreenchimento = true;
    }
});
senha.addEventListener('keyup', () => {

    if (senha.value.length <= 5) {
        labelSenha.setAttribute('style', 'color:red');
        labelSenha.innerHTML = '*Senha *insira no minimo 6 caracteres';
        senha.setAttribute('style', 'border-color: red');
    } else {
        labelSenha.setAttribute('style', 'color:green');
        labelSenha.innerHTML = 'Senha';
        senha.setAttribute('style', 'border-color: green');
        validPreenchimento = true;
    }
});
repetirSenha.addEventListener('keyup', () => {
//verifica se os dados do campo repitir senha é igual ao campo senha
    if (senha.value !== repetirSenha.value) {
        labelRepitirSenha.setAttribute('style', 'color:red');
        labelRepitirSenha.innerHTML = '*Confirme a sua senha *As senhas não conferem';
        repetirSenha.setAttribute('style', 'border-color: red');
    } else {
        labelRepitirSenha.setAttribute('style', 'color:green');
        labelRepitirSenha.innerHTML = 'Confirme a senha';
        repetirSenha.setAttribute('style', 'border-color: green');
        validPreenchimento = true;
    }
});
//=============Cadastro=============
//função cadastrar com envio de dados dos campos nome, usuário, senha e repitir senha para o Local Storage
function cadastrar() {
//ao clicar no botão de cadastro é feito uma validação no campos para verificar se estão todos preenchidos ultilizado
//a variavel (validPreenchimento) e atibuindo a condição se/senão para mudar as cores do input e do label.
//pega os valores do formulario de cadastro e cria um objeto 
//com o JSON.parse -> Converte o que foi digitado em JSON na variavel listaUser no localStorage
//getItem -> agora pega os itens  listaUser || '[]' cria uma lista vazia se existir uma lista de usuário e incrementa outra lista
    if (validPreenchimento === false) {
        msgError.setAttribute('style', 'display:block');
        msgError.innerHTML = '<strong>Preencha todos campos..</strong>';
        msgSuccess.setAttribute('style', 'display:none');
    } else if (senha.value.length <= 5) {
        msgError.setAttribute('style', 'display:block');
        msgError.innerHTML = '<strong>Sua senha tem que tem no minimo 6 caracteres..</strong>';
        msgSuccess.setAttribute('style', 'display:none');
    } else if (senha.value !== repetirSenha.value) {
        msgError.setAttribute('style', 'display:block');
        msgError.innerHTML = '<strong>As senhas não conferem...</strong>';
        msgSuccess.setAttribute('style', 'display:none');
    } else {
        let listaUser = JSON.parse(localStorage.getItem('listaUser') || '[]');
        listaUser.push(
                {
                    nome: nome.value,
                    userCad: usuario.value,
                    senhaCad: senha.value
                }
        );
//após validar os campos define o item no localStorage e converte o a listaUser em JSON.stringfy
//redireciona para pagina de login
        localStorage.setItem('listaUser', JSON.stringify(listaUser));
        msgSuccess.setAttribute('style', 'display:block');
        msgSuccess.innerHTML = '<strong>Cadastrando usuário...</strong>';
        msgError.setAttribute('style', 'display:none');
        msgError.innerHTML = '';
        setTimeout(() => {
            window.location.href = 'http://127.0.0.1:5502/index.html';
        }, 1000);//tempo para redirecionar a pagina de login
    }
};
//=============Entrar=============
//função entrar pega os dados do loacalStore e valida com os dados digitados no campo usuario e senha para conectar
function entrar() {

    let userName = document.querySelector('#userName');
    let labelUser = document.querySelector('#labelUser');

    let passSenha = document.querySelector('#passSenha');
    let labelPassSenha = document.querySelector('#labelPassSenha');

    let msgError = document.querySelector('#msgError');

    let listaUser = [];

    let userValid = {
        nome: '',
        user: '',
        senha: ''
    };

    listaUser = JSON.parse(localStorage.getItem('listaUser'));

    listaUser.forEach((item) => {
//aqui está sendo feito a validação do usuario e da senha se ambos estão preenchidos resultando em um mensagem de erro caso esteja vazio 
//caso ambos estejam com dados digitados, validamos se eles estão cadastrados no banco de dados, casos esteja incorreto atribuimos um erro
        if (userName.value === item.userCad && passSenha.value === item.senhaCad) {
            userValid = {
                nome: item.nome,
                user: item.userCad,
                senha: item.senhaCad
            };
        }
    });
//validamos se o campos estão preenchidos
    if (userName.value === '' || passSenha.value === '') {
        labelUser.setAttribute('style', 'color: red');
        userName.setAttribute('style', 'border-color: red');
        labelPassSenha.setAttribute('style', 'color: red');
        passSenha.setAttribute('style', 'border-color: red');
        msgError.setAttribute('style', 'display: block');
        msgError.innerHTML = 'Digite o seu nome de usuario e senha';
//validamos se os dados digitados estão cadastrados,caso seja validado o usuário e senha conectamos a nossa pagina de musicas 
    } else if (userName.value === userValid.user && passSenha.value === userValid.senha) {
        window.location.href = 'http://127.0.0.1:5502/inicio/index.html';
        let mathRandom = Math.random().toString(16).substr(2);
        let token = mathRandom + mathRandom;
        localStorage.setItem('token', token);
        localStorage.setItem('userLogado', JSON.stringify(userValid));
// se não validar atribuimos uma mensagem de erro 
    } else {
        labelUser.setAttribute('style', 'color: red');
        userName.setAttribute('style', 'border-color: red');
        labelPassSenha.setAttribute('style', 'color: red');
        passSenha.setAttribute('style', 'border-color: red');
        msgError.setAttribute('style', 'display: block');
        msgError.innerHTML = 'Usuário ou senha incorreta';
    }
}
;




