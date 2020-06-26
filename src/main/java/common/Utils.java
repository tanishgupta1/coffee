package common;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

public class Utils {

  public static Long getLongValue(Object v) {
    return (new Double((Double)v)).longValue();
  }

  public static <T> CompletableFuture<Void> allOf(List<CompletionStage<T>> completionStages) {
    return CompletableFuture.allOf((CompletableFuture[])((List)completionStages.stream().map(CompletionStage::toCompletableFuture).collect(Collectors.toList())).toArray((CompletableFuture[])(new CompletableFuture[completionStages.size()])));
  }
}
