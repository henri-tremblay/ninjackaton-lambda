package com.octo.spike;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FooBarQixTest {


    private final LambdaFooBarQix fooBar = new LambdaFooBarQix();

    @Test
	void should_return_BAR() {
        String bar = LambdaFooBarQix.charToCode('5');
        assertThat(bar).isEqualTo("BAR");
    }

    @Test
	void should_return_null() {
        String bar = LambdaFooBarQix.charToCode('1');
        assertThat(bar).isNull();
    }

    @Test
	void should_return_number() {
        // When
		String result = fooBar.translate(1);
		
		// Then
		assertThat(result).isEqualTo("1");
	}

	@Test
	void should_return_foo() {
		// When
		String result = fooBar.translate(3);
		
		// Then
		assertThat(result).isEqualTo("FOOFOO");
	}
	
	@Test
	void should_return_bar() {
		// When
		String result = fooBar.translate(5);
		
		// Then
		assertThat(result).isEqualTo("BARBAR");
	}
	
	@Test
	void should_return_qix() {
		// When
		String result = fooBar.translate(7);
		
		// Then
		assertThat(result).isEqualTo("QIXQIX");
	}

	@Test
	void should_return_foobar() {
		// When
		String result = fooBar.translate(15);
		
		// Then
		assertThat(result).isEqualTo("FOOBARBAR");
	}
	
	@Test
	void should_return_fooqix() {
		// When
		String result = fooBar.translate(21);
		
		// Then
		assertThat(result).isEqualTo("FOOQIX");
	}
	
	@Test  void should_return_bar_qix_foo_bar() {
		// When
		String result = fooBar.translate(35);
		
		// Then
		assertThat(result).isEqualTo("BARQIXFOOBAR");
	}
}
