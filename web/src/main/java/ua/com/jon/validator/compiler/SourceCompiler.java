package ua.com.jon.validator.compiler;

import javax.tools.*;
import java.util.ArrayList;


public class SourceCompiler {
    public CompilationResult compileSourceCode(String className, String classCode) throws Exception {
        if(className == null) {
            throw new IllegalArgumentException("The class name is null");
        }

        if(classCode == null) {
            throw new IllegalArgumentException("The code of class is null");
        }

        if(classCode.isEmpty()) {
            throw new IllegalArgumentException("Code of class is empty");
        }
        // результат: успех компиляции, скомпилированные объекты, ошибки
        CompilationResult compilationResult = new CompilationResult();


        // готовим компилятор и менеджер файлов
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        ClassFileManager fileManager = new ClassFileManager(compiler.getStandardFileManager(null, null, null));
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();

        // компилируем все исходники проекта
        ArrayList<JavaFileObject> javaFileObjects = new ArrayList<>();
//        for (TaskClass taskClass : taskClasses) {
//            String name = taskClass.getClassName().replaceAll("['.']java", "");
        JavaFileObject file = new JavaSourceFromString(className, classCode);
        javaFileObjects.add(file);
//        }

        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, null, null, javaFileObjects);

        boolean isSuccess = task.call();

        // собираем информацию об ошибках
        // todo if !isSuccess
        for (Diagnostic diagnostic : diagnostics.getDiagnostics()) {
            String strMessage = diagnostic.getMessage(null);
            strMessage = strMessage.replace("<", "&lt");
            strMessage = strMessage.replace(">", "&gt");
            compilationResult.addCompilationError(diagnostic.getLineNumber(), strMessage);
        }


        compilationResult.setSuccess(isSuccess);
        compilationResult.setClassFileManager(fileManager);

        // возвращаем результат
        return compilationResult;

    }


}




