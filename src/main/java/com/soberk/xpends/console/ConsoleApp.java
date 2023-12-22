package com.soberk.xpends.console;

import com.soberk.xpends.account.dto.request.AccountWithSpotReqDto;
import com.soberk.xpends.account.dto.response.AccountDto;
import com.soberk.xpends.account.service.AccountService;
import com.soberk.xpends.account.service.AccountServiceImpl;
import com.soberk.xpends.category.dto.request.CategoryReqDto;
import com.soberk.xpends.category.dto.response.CategoryDto;
import com.soberk.xpends.category.service.CategoryService;
import com.soberk.xpends.category.service.CategoryServiceImpl;
import com.soberk.xpends.console.enums.*;
import com.soberk.xpends.console.constants.Options;
import com.soberk.xpends.core.interfaces.TransactionOperations;
import com.soberk.xpends.spot.dto.request.SpotPatchReqDto;
import com.soberk.xpends.spot.dto.request.SpotReqDto;
import com.soberk.xpends.spot.dto.response.SpotDto;
import com.soberk.xpends.spot.service.SpotService;
import com.soberk.xpends.spot.service.SpotServiceImpl;
import com.soberk.xpends.transaction.dto.request.TransactionReqDto;
import com.soberk.xpends.transaction.dto.response.TransactionDto;
import com.soberk.xpends.console.enums.TransactionType;
import com.soberk.xpends.transaction.service.TransactionService;
import com.soberk.xpends.transaction.service.TransactionServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.BiFunction;

public class ConsoleApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Options OPTIONS = new Options();
    private static final AccountService accountService = new AccountServiceImpl();
    private static final CategoryService categoryService = new CategoryServiceImpl();
    private static final SpotService spotService = new SpotServiceImpl();
    private static final TransactionService transactionService = new TransactionServiceImpl();

    public static void main (String[] args){
        try {
            System.out.println((char)27+"[31m****[EXPENSAPP]****");
            showMenuOptions();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void connectToDB(){
        String url = "jdbc:h2:~/test"; // URL de conexión a la base de datos H2
        String username = "user"; // Nombre de usuario de la base de datos
        String password = "1234"; // Contraseña de la base de datos

        try {
            // Establecer la conexión
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS usuarios (id INT PRIMARY KEY, nombre VARCHAR(50))");
            statement.executeUpdate("INSERT INTO usuarios VALUES(1,'Karlo')");
            System.out.println(connection);
            // Realizar operaciones en la base de datos

            // Cerrar la conexión
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void showMenuOptions() throws SQLException {
        System.out.println((char)27 + "[34m\n___MENU___"+(char)27+"[32m");
        int option = launchOptions(OPTIONS.MENU.values()) -1;
        if(option < 0) {
            showMenuOptions();
            return;
        }
        MenuOpt value = MenuOpt.values()[option];
        switch (value){
            case ACCOUNTS -> {
                showAccountOptions();
                break;
            }
            case CATEGORIES -> {
                showCategoryOptions();
                break;
            }
            default -> throw new IllegalStateException("Unexpected value: " + value);
        }
    }

    public static void showAccountOptions() throws SQLException {
        System.out.println((char)27 + "[34m\n___CUENTAS___"+(char)27+"[33m");
        Collection<String> options = new ArrayList<>(OPTIONS.ACCOUNT.values());
        int option = launchOptions(options) -1;
        if(option < 0) {
            showMenuOptions();
            return;
        } else if(option > options.size()-1) {
            showMenuOptions();
            return;
        }
        AccountOpt value = AccountOpt.values()[option];
        switch (value){
            case SELECT -> {
                AccountDto account = selectAccounts();
                if(account != null)
                    showSpotOptions(account);
                break;
            }
            case CREATE -> {
                System.out.print("Ingresa el nombre de la cuenta: ");
                String accountName = scanner.nextLine();
                System.out.print("Ingresa una descripción (opcional): ");
                String accountDescription = scanner.nextLine();
                AccountWithSpotReqDto newAccount = new AccountWithSpotReqDto();
                newAccount.setName(accountName);
                newAccount.setDescription(accountDescription);
                SpotReqDto spot = new SpotReqDto();
                spot.setCurrencyId(1L);
                newAccount.setSpot(spot);
                accountService.create(newAccount);
                break;
            }
            case DELETE -> {
                AccountDto account = selectAccounts();
                if(account != null)
                    accountService.delete(account.getId());
                break;
            }
            default -> throw new IllegalStateException("Unexpected value: " + value);
        }
        showAccountOptions();
    }
    private static void showCategoryOptions() throws SQLException {
        System.out.println((char)27 + "[34m\n___CATEGORIAS___"+(char)27+"[33m");
        Collection<String> options = new ArrayList<>(OPTIONS.CATEGORY.values());
        int option = launchOptions(options)-1;
        if(option < 0) {
            showMenuOptions();
            return;
        }else if(option > options.size()-1) {
            showMenuOptions();
            return;
        }
        CategoryOpt value = CategoryOpt.values()[option];
        switch (value){
            case CREATE -> {
                System.out.print("Ingresa el nombre de la categoria: ");
                String categoryName = scanner.nextLine();
                System.out.print("Ingresa una descripción (opcional): ");
                String categoryDescription = scanner.nextLine();
                CategoryReqDto newCategory = new CategoryReqDto();
                newCategory.setName(categoryName);
                newCategory.setDescription(categoryDescription);
                categoryService.create(newCategory);
                showCategoryOptions();
                break;
            }
            case DELETE -> {
                CategoryDto category = selectCategories();
                categoryService.remove(category.getId());
                showCategoryOptions();
            }
            default -> throw new IllegalStateException("Unexpected value: " + value);
        }
    }
    private static void showSpotOptions(AccountDto account) throws SQLException {
        Map<String,Object> params = Map.of("account_id",account.getId());
        Optional<SpotDto> spot = spotService.getAll(params).stream().findFirst();
        if(spot.isEmpty()) {
            SpotReqDto request = new SpotReqDto();
            request.setAccountId(account.getId());
            spotService.create(request);
            showSpotOptions(account);
            return;
        }
        System.out.printf((char)27 + "[34m\n___%s___"+(char)27+"[33m\n",account.getName());
        System.out.printf("ID: %s Saldo: %s\n",account.getId(),spot.get().getAmount());
        int option = launchOptions(OPTIONS.SPOT.values())-1;
        if(option < 0 || option > OPTIONS.SPOT.values().size()-1) {
            showMenuOptions();
            return;
        }
        SpotOpt value = SpotOpt.values()[option];
        switch (value){
            case DEPOSIT -> {
                System.out.print("Ingresa el monto a depositar: ");
                double amount = (double) scanner.nextInt();
                try {
                    SpotPatchReqDto spotDto = new SpotPatchReqDto();
                    spotService.update(spot.get().getId(), amount, TransactionOperations.DEPOSIT);
                    TransactionReqDto transactionDTO = new TransactionReqDto();
                    transactionDTO.setSpotId(spot.get().getId());
                    transactionDTO.setAmount(amount);
                    transactionDTO.setTransactionTypeId(TransactionType.DEPOSIT.value());
                    transactionService.create(transactionDTO);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
            case DRAW -> {
                System.out.print("Ingresa el monto a retirar: ");
                double amount = (double) scanner.nextInt();
                System.out.println("Selecciona una categoría:");
                if(categoryService.getAll(new HashMap<>()).isEmpty()) {
                    System.out.println("No se encontraron categorías");
                    System.out.print("¿Deseas crear una categoría? [s]Sí/[n]No");
                    String confirm = scanner.nextLine();
                    if(confirm.equalsIgnoreCase("s"))
                        showCategoryOptions();
                    else break;
                }
                else {
                    CategoryDto category = selectCategories();
                    if(category != null) {
                        SpotPatchReqDto spotDto = new SpotPatchReqDto();
                        spotService.update(spot.get().getId(), amount, TransactionOperations.DRAW);
                        TransactionReqDto transactionDTO = new TransactionReqDto();
                        transactionDTO.setSpotId(spot.get().getId());
                        transactionDTO.setAmount(amount);
                        transactionDTO.setTransactionTypeId(TransactionType.EXPENSE.value());
                        transactionDTO.setCategoryId(category.getId());
                        transactionService.create(transactionDTO);
                    }
                }
                break;
            }
            case HISTORY -> {
                try {
                    showHistoryOptions(account);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + value);
        }
        showSpotOptions(account);
    }
    private static void showHistoryOptions(AccountDto account) throws SQLException {
        System.out.println((char)27 + "[34m\n___HISTORIAL___"+(char)27+"[33m");
        int option = launchOptions(OPTIONS.HISTORY.values())-1;
        if(option < 0) {
            showSpotOptions(account);
            return;
        }else if(option > OPTIONS.HISTORY.values().size()-1) {
            showMenuOptions();
            return;
        }
        HistoryOpt value = HistoryOpt.values()[option];
                LocalDateTime today = LocalDateTime.now();
        switch (value){
            case GET_ALL -> {
                List<TransactionDto> transactionDtos = transactionService.getAll(new HashMap<>());
                transactionDtos.forEach(t -> System.out.println(t.toString()));
                break;
            }
            case GET_BY_DAY -> {
                System.out.println((char)27 + "[34m\n___HOY___"+(char)27+"[32m");
                Map<String,Object> params = Map.of("date_time", today);
                List<TransactionDto> transactionDtos = transactionService.getAll(params);
                transactionDtos.forEach(t -> System.out.println(t.toString()));
                break;
            }
            case GET_BY_WEEK -> {
                System.out.println((char)27 + "[34m\n___ESTA SEMANA___"+(char)27+"[32m");
                boolean onContinue = true;
                int count = 0;
                do {
                    String day = today.getDayOfWeek().minus(count).name().toLowerCase();
                    if (day.equals("monday")) onContinue = false;
                    else count++;
                } while (onContinue);
                LocalDateTime minDate = today.minusDays(count + 1);
                LocalDateTime maxDate = today.plusDays(1);
                List<TransactionDto> transactionDtos = transactionService.getByDate(minDate,maxDate);
                transactionDtos.forEach(t -> System.out.println(t.toString()));
                break;
            }
            case GET_BY_MONTH -> {
                System.out.println((char)27 + "[34m\n___ESTE MES___"+(char)27+"[32m");
                LocalDateTime minDate = LocalDateTime.of(today.getYear(),today.getMonth(),1,0,0);
                LocalDateTime maxDate = minDate.plusMonths(1);
                List<TransactionDto> transactionDtos = transactionService.getByDate(minDate,maxDate);
                transactionDtos.forEach(t -> System.out.println(t.toString()));
                break;
            }
            case GET_TRANSACTION -> {
                System.out.print("Ingresa el id de la transacción a buscar: ");
                String id = scanner.nextLine();
                System.out.println((char)27 + "[34m\n___RESULTADOS___"+(char)27+"[32m");
                TransactionDto transactionDTO = transactionService.get(UUID.fromString(id));
                System.out.println(transactionDTO.toString());
                break;
            }
            default -> throw new IllegalStateException("Unexpected value: " + value);
        }
    }

    private static int launchOptions(Collection<String> options){
        int currIndex = 0;
        BiFunction<Integer,String,String> newMsg = (index, msg) -> String.format("[%s] %s",index,msg);
        for(String option : options){
            String msg;
            if(currIndex == 0) {
                msg = newMsg.apply(currIndex, "Volver atrás");
                System.out.println(msg);
            }
            currIndex++;
            msg = newMsg.apply(currIndex,option);
            System.out.println(msg);
        }
        System.out.println(newMsg.apply(++currIndex,"Volver al inicio"));
        System.out.print((char)27+ "[0mIngresa una opcion: ");
        int option = scanner.nextInt();
        scanner.nextLine();
        return option;
    }

    private static CategoryDto selectCategories() throws SQLException {
        List<CategoryDto> categories = categoryService.getAll(new HashMap<>());
        List<String> list = categories.stream().map(CategoryDto::getName).toList();
        if(list.isEmpty()) {
            System.out.println("No hay categorías disponibles");
            return null;
        }
        int option = launchOptions(list)-1;
        return option < 0 || option >= list.size() ? null : categories.get(option);
    }

    private static AccountDto selectAccounts() throws SQLException {
        List<AccountDto> accounts = accountService.getAll();
        List<String> list = accounts.stream().map(acc -> acc.getId() + " - " + acc.getName()).toList();
        if(list.isEmpty()){
            System.out.println("No hay cuentas disponibles");
            return null;
        }
        int option = launchOptions(list) -1;
        return option < 0 || option >= list.size() ? null : accounts.get(option);
    }
}
