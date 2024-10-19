package co.com.ancas.uses_cases.interfaces;

@FunctionalInterface
public interface IUseCase<INPUT, OUTPUT> {

    OUTPUT execute(INPUT input);
}
