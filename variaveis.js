var varVariavel = "É uma variável que pode ser alterada e usada em todo ambiente/interface";
console.log("varVariavel: ", varVariavel);

const constVariavel = "É uma variável somente leitura, a qual não poderá ser alterada, ocasionando um erro no console";
console.log("constVariavel: ", constVariavel);

let letVariavel = "É uma variável que será declarada apenas no mesmo bloco de códigos e, logo em seguida, será eliminada da memória/interface";
console.log("letVariavel: ", letVariavel);

varVariavel = "Nesta linha a variável varVariavel poderá ser alterada";
console.log("varVariavel: ", varVariavel);

letVariavel = "Nesta linha a variável letVariavel poderá ser alterada, pois ainda não encerrou o bloco de códigos";
console.log("letVariavel: ", letVariavel);

// constVariavel = "Esta linha não será executada, pois a variável constVariavel é somente leitura";
// console.log("constVariavel: ", constVariavel);