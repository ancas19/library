package co.com.ancas.uses_cases.interfaces;

@FunctionalInterface
public interface IUseCaseResult<Output> {
    Output execute();
}
