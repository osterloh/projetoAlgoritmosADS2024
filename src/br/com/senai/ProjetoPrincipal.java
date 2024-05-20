package br.com.senai;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class ProjetoPrincipal {

    static Scanner tec = new Scanner(System.in);
    static ArrayList<Pessoa> pessoas = new ArrayList<>();

    public static void main(String[] args) {
        int opcao;
        boolean repetir = true;
        do{
            opcao = menu();
            int idPessoa;
            switch (opcao) {
                case 1 -> {
                    Pessoa pessoa = cadastrar();
                    pessoas.add(pessoa);
                    buscarComDados(pessoas.size());
                }
                case 2 -> {
                    if(pessoasIsEmpty()){
                        break;
                    }
                    listar();
                }
                case 3 -> {
                    if(pessoasIsEmpty()){
                        break;
                    }
                    buscar();
                }
                case 4 -> {
                    if(pessoasIsEmpty()){
                        break;
                    }
                    listar();
                    System.out.println("Informe o ID da pessoa");
                    idPessoa = tec.nextInt();
                    Pessoa pessoa = buscar(idPessoa);
                    Pessoa pessoaEditada = editar(pessoa);
                    pessoas.set(idPessoa - 1, pessoaEditada);
                    buscarComDados(idPessoa);
                }
                case 5 -> {
                    if(pessoasIsEmpty()){
                        break;
                    }
                    listar();
                    System.out.println("Informe o ID da pessoa");
                    idPessoa = tec.nextInt();
                    remover(idPessoa);
                }
                case 9 -> repetir = false;
                default -> System.out.println("Opção inválida.");
            }
        } while (repetir);

        tec.close();
        System.out.println("Sistema finalizado!!!");
    }

    public static Boolean pessoasIsEmpty(){
        if (pessoas.isEmpty()){
            System.out.println("Não possui dados cadastrados!");
            return true;
        }
        return false;
    }

    public static int menu(){
        System.out.println("\n##############################\n");
        System.out.println("--- MENU ---");
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Listar");
        System.out.println("3 - Buscar");
        System.out.println("4 - Editar");
        System.out.println("5 - Remover");
        System.out.println("9 - Encerrar o sistema");
        System.out.print("Informe a opção desejada: ");
        return tec.nextInt();
    }

    public static Pessoa cadastrar(){
        System.out.println("--- CADASTRAR ---");
        Pessoa pessoa = new Pessoa();

        System.out.print("Informe o nome: ");
        tec.nextLine();
        pessoa.nome = tec.nextLine();
        System.out.print("Informe a idade: ");
        pessoa.idade = tec.nextInt();

        pessoa.notas = adicionarNotas();
        pessoa.media = calcularMedia(pessoa);

        return pessoa;
    }

    public static Double calcularMedia(Pessoa pessoa){
        double somaDasNotas = 0;
        for (int i = 0; i < pessoa.notas.size(); i++){
            somaDasNotas += pessoa.notas.get(i);
        }
        double media = somaDasNotas / pessoa.notas.size();

        return media;
    }

    public static ArrayList<Double> adicionarNotas(){
        ArrayList<Double> notas = new ArrayList<>();
        String informarNota = "s";
        int count = 1;

        do {
            System.out.print("Informe a "+ count +"ª nota: ");
            notas.add(tec.nextDouble());
            ++count;
            System.out.print("informar outra nota? [s/n]");
            informarNota = tec.next();
        } while (informarNota.equalsIgnoreCase("s"));

        return notas;
    }

    public static void listar(){
        System.out.println("--- LISTAR ---");
        for(int i = 0; i < pessoas.size(); i++){
            String notas = "";
            for(int j = 0; j < pessoas.get(i).notas.size(); j++){
                notas += pessoas.get(i).notas.get(j);
                if (j < (pessoas.get(i).notas.size() - 1)){
                    notas += " | ";
                }
            }
            System.out.println((i + 1) + "ª Pessoa:");
            System.out.printf("Nome: %s, Idade: %d, Notas: %s, Média: %.2f",
                    pessoas.get(i).nome,
                    pessoas.get(i).idade,
                    notas,
                    pessoas.get(i).media
            );
            System.out.println("\n_______________________________");
        }
    }

    public static Pessoa buscarComDados(int idPessoa){
        System.out.println("--- BUSCAR ---");
        if(idPessoa <= 0 || idPessoa > pessoas.size()){
            System.out.println("Cadastro não encontrado!");
            return null;
        }
        int id = idPessoa - 1;
        String notas = "";
        for(int j = 0; j < pessoas.get(id).notas.size(); j++){
            notas += pessoas.get(id).notas.get(j);
            if (j < (pessoas.get(id).notas.size() - 1)){
                notas += " | ";
            }
        }
        System.out.printf("Nome: %s, Idade: %d, Notas: %s, Média %.2f",
                pessoas.get(id).nome,
                pessoas.get(id).idade,
                notas,
                pessoas.get(id).media);
        System.out.println("\n_______________________________");
        return pessoas.get(id);
    }

    public static Pessoa buscar(){
        System.out.println("--- BUSCAR ---");

        System.out.print("Informe o ID da pessoa: ");
        int idPessoa = tec.nextInt();
        if(idPessoa <= 0 || idPessoa > pessoas.size()){
            System.out.println("Cadastro não encontrado!");
            return null;
        }
        int id = idPessoa - 1;
        String notas = "";
        for(int j = 0; j < pessoas.get(id).notas.size(); j++){
            notas += pessoas.get(id).notas.get(j);
            if (j < (pessoas.get(id).notas.size() - 1)){
                notas += " | ";
            }
        }
        System.out.printf("Nome: %s, Idade: %d, Notas: %s, Média %.2f",
                pessoas.get(id).nome,
                pessoas.get(id).idade,
                notas,
                pessoas.get(id).media);
        System.out.println("\n_______________________________");
        return pessoas.get(id);
    }

    public static Pessoa buscar(int idPessoa){
        if(idPessoa <= 0 || idPessoa > pessoas.size()){
            System.out.println("Cadastro não encontrado!");
            return null;
        }
        return pessoas.get(idPessoa - 1);
    }

    public static Pessoa editar(Pessoa pessoa){
        System.out.println("--- EDITAR ---");
        if (pessoa == null || Objects.isNull(pessoa)){
            System.out.println("Cadastro não encontrado!");
            return null;
        }
        System.out.println("--- EDITAR DADOS CADASTRADOS ---");
        System.out.println("1 - Nome");
        System.out.println("2 - Idade");
        System.out.println("3 - Notas");
        System.out.print("Informe a opção desejada: ");
        int opcao = tec.nextInt();
        Pessoa pessoaEditada = menuEditarDadosCadastrados(opcao, pessoa);

        return pessoaEditada;
    }

    public static void remover(int idPessoa){
        System.out.println("--- REMOVER ---");
        if (pessoas.size() == 0){
            System.out.println("Não possui dados cadastrados!");
            return;
        }
        if (idPessoa <= 0 || idPessoa > pessoas.size()){
            System.out.println("Cadastro não encontrado!");
            return;
        }
        idPessoa = idPessoa - 1;
        pessoas.remove(idPessoa);
        System.out.println("Cadastro removido com sucesso.");
    }

    public static Pessoa menuEditarDadosCadastrados(int opcao, Pessoa pessoa){
        switch (opcao){
            case 1:
                System.out.println("--- EDITAR NOME ---");
                pessoa = editarNome(pessoa);
                break;
            case 2:
                System.out.println("--- EDITAR IDADE ---");
                pessoa = editarIdade(pessoa);
                break;
            case 3:
                System.out.println("--- EDITAR NOTAS ---");
                pessoa = editarNotasMedia(pessoa);
                break;
            default:
                System.out.println("Opção inválida!!!");
                break;
        }
        return pessoa;
    }

    public static Pessoa editarNome(Pessoa pessoa){
        System.out.print("Informe o nome: ");
        tec.nextLine();
        String nome = tec.nextLine();
        pessoa.nome = nome;
        return pessoa;
    }

    public static Pessoa editarIdade(Pessoa pessoa){
        System.out.print("Informe a idade: ");
        int idade = tec.nextInt();
        pessoa.idade = idade;
        return pessoa;
    }

    public static Pessoa editarNotasMedia(Pessoa pessoa){
        pessoa.notas = adicionarNotas();
        pessoa.media = calcularMedia(pessoa);
        return pessoa;
    }
}
