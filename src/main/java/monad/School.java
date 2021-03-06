package monad;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

// interface java.util.function.Predicate<E>
//interface StudentCriterion {
//  boolean test(Student s);
//}

// interface java.util.function.Function<E, F>
//interface Operation<E, F> {
//  F apply(E e);
//}

//class SmartCriterion implements StudentCriterion {
class SmartCriterion implements Predicate<Student> {
  @Override
  public boolean test(Student s) {
    return s.getGpa() > 3;
  }
}

//class EnthusiasticCriterion implements StudentCriterion {
class EnthusiasticCriterion implements Predicate<Student> {
  @Override
  public boolean test(Student s) {
    return s.getCourses().size() > 3;
  }
}

public class School {
  public static void showAll(List<?> ls) {
    for (Object s : ls) {
      System.out.println("> " + s);
    }
    System.out.println("-------------------------");
  }

//  public static List<Student> getSmartStudents(
//      List<Student> ls, double threshold) {
//
//    List<Student> res = new ArrayList<>();
//
//    for (Student s : ls) {
//      if (s.getGpa() > threshold) {
//        res.add(s);
//      }
//    }
//    return res;
//  }
//
//  public static List<Student> getEnthusiasicStudents(
//      List<Student> ls, int threshold) {
//
//    List<Student> res = new ArrayList<>();
//
//    for (Student s : ls) {
//      if (s.getCourses().size() > threshold) {
//        res.add(s);
//      }
//    }
//    return res;
//  }

  public static <E> List<E> filter(
      Iterable<E> ls, Predicate<E> interesting) {
//      List<Student> ls, StudentCriterion interesting) {

    List<E> res = new ArrayList<>();

    for (E s : ls) {
      if (interesting.test(s)) {
        res.add(s);
      }
    }
    return res;
  }

//  public static <E, F> List<F> change(List<E> l, Operation<E, F> op) {
  public static <E, F> List<F> map(Iterable<E> l, Function<E, F> op) {
    List<F> res = new ArrayList<>();

    for (E s : l) {
      res.add(op.apply(s));
    }
    return res;
  }

  public static void main(String[] args) {
    List<Student> ls = Arrays.asList(
        Student.of("Fred", 3.2, "Math", "Physics"),
        Student.of("Jim", 2.2, "Art"),
        Student.of("Sheila", 3.8,
            "Math", "Physics", "Astrophysics", "Quantum mechanics")
    );

    showAll(ls);
//    showAll(getSmartStudents(ls, 3.7));
//    showAll(getEnthusiasicStudents(ls, 3));
    showAll(filter(ls, new SmartCriterion()));
    showAll(filter(ls, new EnthusiasticCriterion()));
    showAll(filter(ls, (Student s) -> {return s.getGpa() < 3.5;}));

    List<String> words = Arrays.asList("here", "are", "Some", "various", "words");
    showAll(filter(words, (String s) -> s.length() > 4));

    showAll(map(words, s -> s.toUpperCase()));
    showAll(map(words, s -> s.length()));

    SuperIterable<String> names = new SuperIterable<>(
        Arrays.asList("Fred", "Jim", "Sheila")
    );

    for (String n : names) {
      System.out.println("> " + n);
    }
  }
}
