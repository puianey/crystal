package com.puianey.crystal.common.util;

import com.puianey.crystal.common.constant.Constants;
import com.puianey.crystal.common.constant.StringPool;
import com.puianey.crystal.common.exception.ServerException;
import lombok.experimental.UtilityClass;

import java.lang.management.ManagementFactory;
import java.time.Duration;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @Author: puianey
 * @Date: 2019-07-18 09:56
 * @Description:
 */
@UtilityClass
public class $ {

	/**
	 * 断言
	 *
	 * @param obj       目标对象
	 * @param predicate 断言条件
	 * @param error     异常信息
	 * @param <T>       范型
	 */
	public <T> void asserts(T obj, Predicate<T> predicate, String error) {
		requireNonNull(obj, predicate, error);
		if (!predicate.test(obj)) {
			throw new ServerException(Optional.ofNullable(error).orElse(Constants.BASE_ERROR_MSG));
		}
	}

	/**
	 * 断言
	 *
	 * @param obj       目标对象
	 * @param predicate 断言条件
	 * @param error     异常信息
	 * @param <T>       范型
	 */
	public <T> void asserts(T obj, Predicate<T> predicate, Supplier<RuntimeException> error) {
		requireNonNull(obj, predicate, error);
		if (!predicate.test(obj)) {
			throw Optional.ofNullable(error).map(Supplier::get).orElse(new ServerException(Constants.BASE_ERROR_MSG));
		}
	}

	/**
	 * 断言
	 *
	 * @param obj       目标对象
	 * @param predicate 断言条件
	 * @param <T>       范型
	 */
	public <T> void asserts(T obj, Predicate<T> predicate) {
		asserts(obj, predicate, () -> new ServerException(Constants.BASE_ERROR_MSG));
	}

	/**
	 * 扩展判断后传入消费者
	 *
	 * @param obj       目标对象
	 * @param predicate 断言条件
	 * @param consumer  消费者
	 * @param <T>       范型
	 */
	public <T> void ifs(T obj, Predicate<T> predicate, Consumer<T> consumer) {
		requireNonNull(obj, predicate, consumer);
		if (predicate.test(obj)) {
			consumer.accept(obj);
		}
	}

	/**
	 * 扩展判断后传入消费者
	 *
	 * @param obj        目标对象
	 * @param expression 条件
	 * @param consumer   消费者
	 * @param <T>        范型
	 */
	public <T> void ifs(T obj, boolean expression, Consumer<T> consumer) {
		requireNonNull(obj, consumer);
		if (expression) {
			consumer.accept(obj);
		}
	}

	/**
	 * 扩展判断
	 *
	 * @param obj          目标对象
	 * @param predicate    断言条件
	 * @param consumerIf   消费者
	 * @param consumerElse 消费者
	 * @param <T>          范型
	 */
	public <T> void ifElse(T obj, Predicate<T> predicate, Consumer<T> consumerIf, Consumer<T> consumerElse) {
		requireNonNull(obj, predicate, consumerIf, consumerElse);
		if (predicate.test(obj)) {
			consumerIf.accept(obj);
		} else {
			consumerElse.accept(obj);
		}
	}

	/**
	 * 扩展判断
	 *
	 * @param obj          目标对象
	 * @param expression   条件
	 * @param consumerIf   消费者
	 * @param consumerElse 消费者
	 * @param <T>          范型
	 */
	public <T> void ifElse(T obj, boolean expression, Consumer<T> consumerIf, Consumer<T> consumerElse) {
		requireNonNull(obj, consumerIf, consumerElse);
		if (expression) {
			consumerIf.accept(obj);
		} else {
			consumerElse.accept(obj);
		}
	}

	/**
	 * 扩展判断所有入参不为空
	 *
	 * @param obj obj
	 */
	public void requireNonNull(Object... obj) {
		Arrays.stream(obj).forEach(Objects::requireNonNull);
	}

	/**
	 * 按照下标替换字符串
	 *
	 * @param inString    原始字符串
	 * @param start       开始位置(下标)
	 * @param length      长度
	 * @param placeholder 替换的字符
	 * @return String
	 */
	public String replace(String inString, int start, int length, String placeholder) {
		Objects.requireNonNull(inString);
		Objects.requireNonNull(placeholder);
		return replace(new StringBuilder(inString), start, length, placeholder).toString();
	}

	/**
	 * 按照下标替换字符串(从后往前)
	 *
	 * @param inString    原始字符串
	 * @param start       开始位置(下标)
	 * @param length      长度
	 * @param placeholder 替换的字符
	 * @return String
	 */
	public String replaceFromBack(String inString, int start, int length, String placeholder) {
		Objects.requireNonNull(inString);
		Objects.requireNonNull(placeholder);
		return replace(new StringBuilder(inString).reverse(), start, length, placeholder).reverse().toString();
	}

	private StringBuilder replace(StringBuilder stringBuilder, int start, int length, String placeholder) {
		var startIndex = (start < stringBuilder.length() && start >= 0) ? start : (start < 0 ? 0 : stringBuilder.length());
		length = Math.max(length, 0);
		var endIndex = Math.min(startIndex + length, stringBuilder.length());
		stringBuilder.replace(startIndex, endIndex, placeholder.repeat(endIndex - startIndex));
		return stringBuilder;
	}

	/**
	 * 返回应用启动到现在的时间
	 *
	 * @return Duration
	 */
	public Duration getUpTime() {
		var upTime = ManagementFactory.getRuntimeMXBean().getUptime();
		return Duration.ofMillis(upTime);
	}

	/**
	 * 返回输入的JVM参数列表
	 *
	 * @return String
	 */
	public String getJvmArguments() {
		var vmArguments = ManagementFactory.getRuntimeMXBean().getInputArguments();
		return String.join(StringPool.SPACE, vmArguments);
	}

	/**
	 * 获取CPU核数
	 *
	 * @return int
	 */
	public int getCpuNum() {
		return Runtime.getRuntime().availableProcessors();
	}

}
