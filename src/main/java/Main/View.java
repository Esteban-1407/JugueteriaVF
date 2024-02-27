package Main;

import mapping.dtos.ToyDTO;
import model.ToyType;
import services.ToyStorelmpl;

import java.util.InputMismatchException;
import java.util.Scanner;

public class View {
    public static void main(String[] args) {
        ToyStorelmpl toyStore = new ToyStorelmpl();
        Scanner scanner = new Scanner(System.in);

        // Menú de opciones
        String menu = "Bienvenido a la tienda de juguetes\n" +
                "1. Agregar un juguete\n" +
                "2. Buscar un juguete\n" +
                "3. Incrementar la cantidad de un juguete\n" +
                "4. Disminuir la cantidad de un juguete\n" +
                "5. Mostrar juguetes por encima de cierto precio\n" +
                "6. Mostrar el total de juguetes\n" +
                "7. Mostrar el precio total de todos los juguetes\n" +
                "8. Salir";

        int opcion = 0;
        do {
            System.out.println(menu);
            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea

                switch (opcion) {
                    case 1:
                        // Agregar un juguete
                        try {
                            System.out.println("Ingrese el nombre del juguete:");
                            String nombreJuguete = scanner.nextLine();
                            System.out.println("Ingrese el tipo del juguete:");
                            ToyType tipoJuguete = ToyType.getTypeByValue(Integer.parseInt(scanner.next()));
                            System.out.println("Ingrese la cantidad del juguete:");
                            int cantidad = scanner.nextInt();
                            System.out.println("Ingrese el precio del juguete:");
                            double precio = scanner.nextDouble();
                            ToyDTO newToy = new ToyDTO(nombreJuguete, tipoJuguete, cantidad, (int) precio);
                            toyStore.addToy(newToy);
                            System.out.println("Juguete agregado exitosamente.");
                        } catch (Exception e) {
                            System.out.println("Error al agregar un juguete: " + e.getMessage());
                        }
                        break;
                    case 2:

                        try {
                            System.out.println("Ingrese el nombre del juguete a buscar:");
                            String nombreBuscar = scanner.nextLine();
                            ToyDTO toy = toyStore.search(nombreBuscar);
                            System.out.println("Juguete encontrado: " + toy);
                        } catch (Exception e) {
                            System.out.println("Error al buscar el juguete: " + e.getMessage());
                        }
                        break;
                    case 3:
                        try {
                            System.out.println("Ingrese el nombre del juguete:");
                            String nombreJugueteIncrementar = scanner.nextLine();
                            System.out.println("Ingrese la cantidad para incrementar:");
                            int cantidadIncrementar = scanner.nextInt();
                            ToyDTO toyToIncrease = new ToyDTO(nombreJugueteIncrementar, null, 0, 0);
                            toyStore.increase(toyToIncrease, cantidadIncrementar);
                            System.out.println("Cantidad del juguete incrementada exitosamente.");
                        }
                        catch (NumberFormatException e) {
                            System.out.println("Error: El formato de la cantidad no es válido.");
                        }
                        catch (Exception e) {
                            System.out.println("Error al incrementar la cantidad del juguete: " + e.getMessage());
                        }
                        break;
                    case 4:
                        try {
                            System.out.println("Ingrese el nombre del juguete:");
                            String nombreJugueteDisminuir = scanner.nextLine();
                            System.out.println("Ingrese la cantidad para disminuir:");
                            int cantidadDisminuir = scanner.nextInt();
                            ToyDTO toyToDecrease = new ToyDTO(nombreJugueteDisminuir, null, 0, 0);
                            toyStore.decrease(toyToDecrease, cantidadDisminuir);
                            System.out.println("Cantidad del juguete disminuida exitosamente.");
                        }catch (NumberFormatException e) {
                            System.out.println("Error: El formato de la cantidad no es válido.");
                        }
                        catch (Exception e) {
                            System.out.println("Error al disminuir la cantidad del juguete: " + e.getMessage());
                        }
                        break;
                    case 5:
                        try {
                            System.out.println("Ingrese el precio mínimo:");
                            double precioMinimo = scanner.nextDouble();
                            scanner.nextLine(); // Consumir el salto de línea
                            System.out.println("Juguetes por encima de " + precioMinimo + ":\n");
                            for (ToyDTO toy : toyStore.showToysAbove(precioMinimo)) {
                                System.out.println(toy);
                            }
                        }catch (NumberFormatException e) {
                            System.out.println("Error: El formato del precio no es válido.");
                        }
                        catch (Exception e) {
                            System.out.println("Error al mostrar juguetes por encima de cierto precio: " + e.getMessage());
                        }
                        break;
                    case 6:

                        int totalJuguetes = toyStore.totalToys();
                        System.out.println("Total de juguetes en la tienda: " + totalJuguetes);
                        break;
                    case 7:

                        int precioTotal = toyStore.totalPriceAllToys();
                        System.out.println("Precio total de todos los juguetes: " + precioTotal);
                        break;
                    case 8:
                        System.out.println("Gracias por visitar la tienda de juguetes. ¡Hasta luego!");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, ingrese un número del 1 al 8.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Por favor, ingrese un número.");
                scanner.nextLine();
            }
        } while (opcion != 8);


        scanner.close();
    }
    }

