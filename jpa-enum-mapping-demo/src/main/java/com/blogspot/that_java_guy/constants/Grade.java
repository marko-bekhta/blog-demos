package com.blogspot.that_java_guy.constants;

import java.util.stream.Stream;

/**
 * @author Marko Bekhta
 */
public enum Grade {
	GRADE_A( 'A', 100, 91 ),
	GRADE_B( 'B', 90, 81 ),
	GRADE_C( 'C', 80, 71 ),
	GRADE_D( 'D', 70, 61 ),
	GRADE_E( 'E', 60, 51 ),
	GRADE_F( 'F', 50, 0 ),;

	public final Character charGrade;
	public final int maxPoints;
	public final int minPoints;

	Grade(Character charGrade, int maxPoints, int minPoints) {
		this.charGrade = charGrade;
		this.maxPoints = maxPoints;
		this.minPoints = minPoints;
	}

	public static Grade byChar(Character charGrade) {
		return Stream.of( values() )
				.filter( grade -> grade.charGrade.equals( charGrade ) )
				.findAny()
				.orElse( null );
	}

}
