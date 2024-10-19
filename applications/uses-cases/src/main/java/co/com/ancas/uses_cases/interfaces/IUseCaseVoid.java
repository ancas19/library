package co.com.ancas.uses_cases.interfaces;

@FunctionalInterface
public interface IUseCaseVoid<INPUT> {
    void execute(INPUT input);
}
