package jp.co.kokou.sample.extension;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext.Store;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.TestExtensionContext;

import java.util.logging.Logger;

public class TimingExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    static final Logger LOG = Logger.getLogger(TimingExtension.class.getName());

    @Override
    public void beforeTestExecution(TestExtensionContext context) {
        context.getTestMethod().ifPresent(method -> getStore(context).put(method, System.currentTimeMillis()));
    }

    @Override
    public void afterTestExecution(TestExtensionContext context) {
        context.getTestMethod().ifPresent(method -> {
            long start = getStore(context).remove(method, Long.class);
            long duration = System.currentTimeMillis() - start;

            LOG.info(() -> String.format("Method [%s] took %s ms.", method.getName(), duration));
        });
    }

    private Store getStore(TestExtensionContext context) {
        return context.getStore(Namespace.create(getClass(), context));
    }
}
