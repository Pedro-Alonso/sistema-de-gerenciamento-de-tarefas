package SistemaGerenciamentoTarefas.src.Classes;

import java.util.ArrayList;
import java.util.function.Function;

public class Utils {

  /**
   * Method to get all elements of a given ArrayList<T> and return a String with their names, using the equivalent getName() method of the T class
   * @param array The ArrayList<> of given class T to be used -> {@link ArrayList}
   * @param getNameFunc Equivalent function of getName() on T class -> {@link Function}
   *
   * @return {@link String}
   * Example: getListString(tasks, Task::getName);
   */
  public static <T> String getListString(
    ArrayList<T> array,
    Function<T, String> getNameFunc
  ) {
    if (array == null || array.isEmpty()) return "N/A";

    StringBuilder returnString = new StringBuilder();
    array.forEach(t -> {
      returnString.append(getNameFunc.apply(t)).append(", ");
    });

    return returnString.substring(0, returnString.length() - 2);
  }
}
