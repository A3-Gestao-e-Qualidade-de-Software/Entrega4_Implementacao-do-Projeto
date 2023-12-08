package br.com.a3.hotel;

import br.com.a3.hotel.junitTest.*;
import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("junitTest")
@SelectClasses({HospedesDAOTest.class, HospedesViewTest.class, UsuarioDAOTest.class, UsuarioModelTest.class, UsuarioViewTest.class})
public class SuiteTest {
}
