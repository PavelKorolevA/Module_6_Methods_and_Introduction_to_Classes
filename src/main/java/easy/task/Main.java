package easy.task;

public class Main {
    public static void main(String[] args) {
        House house = new House("Анетты Басс 2", 2023);
        System.out.println("Дом по адресу: " + house.address + ", год постройки: " + house.constructionYear);
    }
}
