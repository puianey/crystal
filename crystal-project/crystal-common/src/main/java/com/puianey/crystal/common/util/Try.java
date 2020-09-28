package com.puianey.crystal.common.util;

import com.puianey.crystal.common.constant.Constants;
import com.puianey.crystal.common.exception.ServerException;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @Author: puianey
 * @Date: 2019-07-18 12:13
 * @Description:
 */
@Slf4j
@UtilityClass
public class Try {

	public <T, R> Function<T, R> of(UncheckedFunction<T, R> mapper, R defaultR) {
		Objects.requireNonNull(mapper);
		return t -> {
			try {
				return mapper.apply(t);
			} catch (Throwable e) {
				Optional.ofNullable(defaultR).orElseThrow(() -> new ServerException(Constants.BASE_ERROR_MSG));
				log.error("受检异常", e);
				return defaultR;
			}
		};
	}

	public <T> Consumer<T> of(UncheckedConsumer<T> mapper) {
		Objects.requireNonNull(mapper);
		return t -> {
			try {
				mapper.accept(t);
			} catch (Throwable e) {
				throw new ServerException(Constants.BASE_ERROR_MSG);
			}
		};
	}

	public <T> Supplier<T> of(UncheckedSupplier<T> mapper, T defaultT) {
		Objects.requireNonNull(mapper);
		return () -> {
			try {
				return mapper.get();
			} catch (Throwable e) {
				Optional.ofNullable(defaultT).orElseThrow(() -> new ServerException(Constants.BASE_ERROR_MSG));
				log.error("受检异常", e);
				return defaultT;
			}
		};
	}

	public <T> Predicate<T> of(UncheckedPredicate<T> mapper, Boolean defaultV) {
		Objects.requireNonNull(mapper);
		return t -> {
			try {
				return mapper.test(t);
			} catch (Throwable e) {
				Optional.ofNullable(defaultV).orElseThrow(() -> new ServerException(Constants.BASE_ERROR_MSG));
				log.error("受检异常", e);
				return defaultV;
			}
		};
	}

	public Runnable of(UncheckedRunnable runnable) {
		Objects.requireNonNull(runnable);
		return () -> {
			try {
				runnable.run();
			} catch (Throwable e) {
				throw new ServerException(Constants.BASE_ERROR_MSG);
			}
		};
	}

	public <T> Callable<T> of(UncheckedCallable<T> callable, T defaultT) {
		Objects.requireNonNull(callable);
		return () -> {
			try {
				return callable.call();
			} catch (Throwable e) {
				Optional.ofNullable(defaultT).orElseThrow(() -> new ServerException(Constants.BASE_ERROR_MSG));
				log.error("受检异常", e);
				return defaultT;
			}
		};
	}

	public <T> Comparator<T> of(UncheckedComparator<T> comparator, Integer defaultV) {
		Objects.requireNonNull(comparator);
		return (T o1, T o2) -> {
			try {
				return comparator.compare(o1, o2);
			} catch (Throwable e) {
				Optional.ofNullable(defaultV).orElseThrow(() -> new ServerException(Constants.BASE_ERROR_MSG));
				log.error("受检异常", e);
				return defaultV;
			}
		};
	}

	@FunctionalInterface
	public interface UncheckedFunction<T, R> {
		@NonNull
		R apply(@NonNull T t) throws Throwable;
	}

	@FunctionalInterface
	public interface UncheckedConsumer<T> {
		void accept(@NonNull T t) throws Throwable;
	}

	@FunctionalInterface
	public interface UncheckedSupplier<T> {
		@NonNull
		T get() throws Throwable;
	}

	@FunctionalInterface
	public interface UncheckedPredicate<T> {
		boolean test(@NonNull T t) throws Throwable;
	}

	@FunctionalInterface
	public interface UncheckedRunnable {
		void run() throws Throwable;
	}

	@FunctionalInterface
	public interface UncheckedCallable<T> {
		@NonNull
		T call() throws Throwable;
	}

	@FunctionalInterface
	public interface UncheckedComparator<T> {
		int compare(T o1, T o2) throws Throwable;
	}

}
