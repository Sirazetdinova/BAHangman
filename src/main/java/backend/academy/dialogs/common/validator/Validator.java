package backend.academy.dialogs.common.validator;

public interface Validator<T> {
    void validate(T input);
}
