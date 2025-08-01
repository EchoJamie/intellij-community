// "Convert record to class" "true-preview"
import java.lang.annotation.*;


@Target(ElementType.METHOD)
@interface MethodAnno {
  int value();
}

record <caret>Person(
        @MethodAnno @Deprecated String name,
        @MethodAnno int age,
        @MethodAnno Address address
) {
    record Address(String street, int number) {}
}
