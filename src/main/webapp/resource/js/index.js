var serviceLocation = "ws://" + document.location.host + document.location.pathname + "tetris";
//Número de linhas e colunas do tabuleiro
var numColunas = 10, numLinhas = 20;
//Colunas do tabuleiro
var colunas = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J"];

//Vai guardar as informações de cada jogador vindas do servidor
var jogador = null;
var oponente = null;

document.load = criarTabuleiro("tb1"), criarTabuleiro("tb2"), conectarAoServidorDoJogo();

//Função que cria tabuleiro dinamicamente
function criarTabuleiro(tab) {
    var tabuleiro = document.getElementById(tab);

    for (var i = 1; i <= numLinhas; i++) {

        var linha = document.createElement("tr");

        for (var j = 1; j <= numColunas; j++) {
            var celula = document.createElement("td");
            celula.setAttribute("id", i + colunas[j - 1]);
            celula.setAttribute("class", "celulas");
            celula.setAttribute("style", "background:grey");
            linha.appendChild(celula);
        }
        tabuleiro.appendChild(linha);
    }

}


function conectarAoServidorDoJogo() {
    ws = new WebSocket(serviceLocation);

    ws.onmessage = lerDados;

    ws.onclose = function () {
        ws.close();
    };

    ws.onerror = () => {
        console.log("erro");
    };

}
//Esta função é executada, sempre que o servidor manda uma mensagem
function lerDados(evt) {
    var data = JSON.parse(evt.data);
    if (data.tipo === "OPEN") {
        if (jogador === null) {
            jogador = data;
        } else {
            oponente = data;
            console.log("jogador: " + jogador.nome);
            console.log("oponente: " + oponente.nome);
        }
    }else if(data.tipo === "MESSAGE"){
        //...
    }else if(data.tipo === "ENDGAME"){
        //...
    }
}
;

document.getElementById("sair").addEventListener("click", function (e) {
    ws.close();
});


function geraObjRandom()
{
    var num1 = parseInt(Math.random() * 6);
    switch (num1)
    {
        case 0:
            criaI();
            break;
        case 1:
            criaL();
            break;
        case 2:
            criaO();
            break;
        case 3:
            criaS();
            break;
        case 4:
            criaT();
            break;
        case 5:
            criaZ();
    }
}

// Cria o objeto O
function criaO() {   //azul

}

// Cria o objeto S
function criaS() { //azul
}

//cria o Objeto Z
function criaZ() { //vermelho
}

//cria o Objeto I
function criaI() { // vermelho
}

//Cria o Objeto T
function criaT() { // branco  
}

//Cria o Objeto L
function criaL() { // branco

}



//Atualizar jogadas disponíveis no objeto jogo
function atualizarJogadasDisponiveis(posicaoJogada) {

}







