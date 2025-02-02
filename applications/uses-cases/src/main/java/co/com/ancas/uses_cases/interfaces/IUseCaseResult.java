package co.com.ancas.uses_cases.interfaces;

@FunctionalInterface
public interface IUseCaseResult<OUTPUT> {
    OUTPUT execute();
}
