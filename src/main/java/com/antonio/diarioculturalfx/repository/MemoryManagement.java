package com.antonio.diarioculturalfx.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.antonio.diarioculturalfx.model.Book;
import com.antonio.diarioculturalfx.model.Film;
import com.antonio.diarioculturalfx.model.Media;
import com.antonio.diarioculturalfx.model.Serie;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Classe que representa o banco de dados, ela guarda as séries enquanto o programa está ativo
 */
public class MemoryManagement {
    private ArrayList<Book> books = new ArrayList<Book>();
    private ArrayList<Film> films = new ArrayList<Film>();
    private ArrayList<Serie> series = new ArrayList<Serie>();
    private final String caminho;

    public MemoryManagement(String caminho) {
        this.caminho = caminho;
    }


    public ArrayList<Book> getBooks() {return books;}

    /**
     * Adiciona uma mídia em ordem
     * @param book objeto book
     */
    public void addBook(Book book) {

        Book bookNaoPodeSerNulo = Objects.requireNonNull(book, "Book não pode ser nulo");
        int i = 0;

        while(i < books.size() && books.get(i).getTitle().compareToIgnoreCase(book.getTitle()) < 1) {
            i++;
        }
        books.add(i, book);
    }
    public ArrayList<Film> getFilms() {
        return films;
    }

    /**
     * Adiciona uma mídia em ordem
     * @param film objeto film
     */
    public void addFilm(Film film) {

        Film filmNaoPodeSerNulo = Objects.requireNonNull(film, "Film não pode ser nulo");
        int i = 0;

        while(i < films.size() && films.get(i).getTitle().compareToIgnoreCase(film.getTitle()) < 1) {
            i++;
        }
        films.add(i, film);
    }
    public ArrayList<Serie> getSeries() {
        return series;
    }

    /**
     * Adiciona uma mídia em ordem
     * @param serie objeto serie
     */
    public void addSerie(Serie serie) {

        Serie serieNaoPodeSerNulo = Objects.requireNonNull(serie, "Serie não pode ser nulo");
        int i = 0;

        while(i < series.size() && series.get(i).getTitle().compareToIgnoreCase(serie.getTitle()) < 1) {
            i++;
        }
        series.add(i, serie);
    }

    private Gson createGson() {
        // Salva as lista com os seus respectivos tipos para quando ocorrer o carregamento, nao seja instanciada nenhum obj Media(abstrata)
        RuntimeTypeAdapterFactory<Media> mediaAdapterFactory = RuntimeTypeAdapterFactory
                .of(Media.class, "type")
                .registerSubtype(Film.class, "filme")
                .registerSubtype(Serie.class, "serie")
                .registerSubtype(Book.class, "livro");

        return new GsonBuilder()
            .registerTypeAdapterFactory(mediaAdapterFactory)
            .setPrettyPrinting()
            .create();
    }

    /**
     * Busca o arquivo informado pelo parametro 'caminho', se nao existir, ele o cria ou retorna uma exception
     */
    public File buscaOuCriarArquivo() {
        try{
            File file = new File(caminho);
            if (file.createNewFile()) {
                System.out.println("Arquivo criado: " + file.getName());
            } else {
                System.out.println("Arquivo já existe.");
            }
            return file;
        } catch (IOException e) {
            throw new RuntimeException("Erro ao criar ou buscar aquivo "+e);
        }
    }

    /**
     * Escreve o texto no arquivo informado
     * @param texto string
     * @param caminho string
     */
    public void escreveArquivo(String texto, String caminho) {
        try (FileWriter fw = new FileWriter(caminho)){
            fw.write(texto);
            System.out.println("Arquivo salvo com sucesso!");
        } catch (IOException e){
            System.out.println("Erro ao escrever arquivo: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Tranforma os atributos da classe em String com formatação json
     */
    public void salvaArquivosBd() {

        Map<String, List<Map<String, Object>>> jsonEstruturado = new LinkedHashMap<>();

        jsonEstruturado.put("livros",serializaComTipo(this.books, "livro"));
        jsonEstruturado.put("filmes", serializaComTipo(this.films, "filme"));
        jsonEstruturado.put("series", serializaComTipo(this.series, "serie"));


        Gson gson = createGson();

        // Chama a funcao que escreve o arquivo
        escreveArquivo(gson.toJson(jsonEstruturado), caminho);
    }

    private List<Map<String, Object>> serializaComTipo(List<? extends Media> lista, String tipo) {
        List<Map<String, Object>> listaFinal = new ArrayList<>();
        for (Media m : lista) {
            // Converte o objeto Media -> Map
            Map<String, Object> objeto = new Gson().fromJson(new Gson().toJson(m), Map.class);
            objeto.put("type", tipo); // Adiciona o campo "type"
            listaFinal.add(objeto);
        }
        return listaFinal;
    }

    /**
     * Reconstroi as classes presentes em um arquivo jsom
     * @param caminho arquivo
     * @return Map de arraylists
     */
    public Map<String, List<Media>> jsonToClass(File caminho) {
        try {
            String response = Files.readString(caminho.toPath());

            if(response.isEmpty()) {
                return new HashMap<>();
            }


            Gson gson = createGson();

            Type tipoMapa = new TypeToken<Map<String, List<Media>>>() {}.getType();
            Map<String, List<Media>> dados = gson.fromJson(response, tipoMapa);
            dados.get("livros");
            return gson.fromJson(response, tipoMapa);
        } catch (NoSuchElementException | IOException e){
            return null;
        }
    }


    /**
     * Popula os arrays com o map obtido pela leitura do json
     * @param response resposta
     */
    public void populaOsArrays(Map<String, List<Media>> response) {
        if(response != null) {
            this.books.clear(); // As listas existentes são limpas
            this.films.clear();
            this.series.clear();

            this.books = response.getOrDefault("livros", Collections.emptyList()).stream()
                    .filter(media -> media instanceof Book) // Garante que é um Book
                    .map(media -> (Book) media)           // Faz o cast seguro para Book
                    .collect(Collectors.toCollection(ArrayList::new)); // Coleta em uma nova ArrayList

            // Preenche ArrayList Films
            this.films = response.getOrDefault("filmes", Collections.emptyList()).stream()
                    .filter(media -> media instanceof Film)
                    .map(media -> (Film) media)
                    .collect(Collectors.toCollection(ArrayList::new));

            // Preenche ArrayList Series
            this.series = response.getOrDefault("series", Collections.emptyList()).stream()
                    .filter(media -> media instanceof Serie)
                    .map(media -> (Serie) media)
                    .collect(Collectors.toCollection(ArrayList::new)); // Completando a linha

        }

    }

    /**
     * Deleta objeto do tipo Film
     * @param film obj film
     */
    public void deleteMedia(Film film){
        films.removeIf(film1 -> film1.equals(film));
    }

    /**
     * Deleta objeto do tipo Book
     * @param book obj book
     */
    public void deleteMedia(Book book){
        books.removeIf(book1 -> book1.equals(book));
    }

    /**
     * Deleta objeto do tipo Serie
     * @param serie obj serie
     */
    public void deleteMedia(Serie serie){
        series.removeIf(serie1 -> serie1.equals(serie));
    }
}
