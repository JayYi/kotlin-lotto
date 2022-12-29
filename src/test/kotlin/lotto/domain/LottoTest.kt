package lotto.domain

import lotto.model.LottoNumber
import lotto.model.LottoNumbers
import lotto.model.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class LottoTest {
    private val winningLottoNumbers = (1..6).map(::LottoNumber)
    private val bonusNumber = LottoNumber(7)
    private val winningLotto = WinningLotto(winningLottoNumbers, bonusNumber)

    @Test
    fun `1등 당첨`() {
        // given
        val userLotto =
            listOf(LottoNumbers(listOf(1, 2, 3, 4, 5, 6).map(::LottoNumber))).let(::Lotto)

        // when
        val lottoPrizeResults = userLotto.matches(winningLotto)

        // then
        assertThat(lottoPrizeResults.count(Rank.FIRST)).isEqualTo(1)
    }

    @Test
    fun `2등 당첨`() {
        // given
        val userLotto =
            listOf(LottoNumbers(listOf(1, 2, 3, 4, 5, 7).map(::LottoNumber))).let(::Lotto)

        // when
        val lottoPrizeResults = userLotto.matches(winningLotto)

        // then
        assertThat(lottoPrizeResults.count(Rank.SECOND)).isEqualTo(1)
    }

    @Test
    fun `3등 당첨`() {
        // given
        val userLotto =
            listOf(LottoNumbers(listOf(1, 2, 3, 4, 5, 8).map(::LottoNumber))).let(::Lotto)

        // when
        val lottoPrizeResults = userLotto.matches(winningLotto)

        // then
        assertThat(lottoPrizeResults.count(Rank.THIRD)).isEqualTo(1)
    }

    @Test
    fun `4등 당첨`() {
        // given
        val userLotto =
            listOf(LottoNumbers(listOf(1, 2, 3, 4, 8, 9).map(::LottoNumber))).let(::Lotto)

        // when
        val lottoPrizeResults = userLotto.matches(winningLotto)

        // then
        assertThat(lottoPrizeResults.count(Rank.FOURTH)).isEqualTo(1)
    }

    @Test
    fun `5등 당첨`() {
        // given
        val userLotto =
            listOf(LottoNumbers(listOf(1, 2, 3, 8, 9, 10).map(::LottoNumber))).let(::Lotto)

        // when
        val lottoPrizeResults = userLotto.matches(winningLotto)

        // then
        assertThat(lottoPrizeResults.count(Rank.FIFTH)).isEqualTo(1)
    }

    @ParameterizedTest
    @MethodSource("provideLosingLotto")
    fun `꽝(담청 결과 없음)`(userLotto: Lotto) {
        // given
        // when
        val lottoPrizeResults = userLotto.matches(winningLotto)

        // then
        assertThat(lottoPrizeResults.size == 0).isTrue
    }

    companion object {
        @JvmStatic
        fun provideLosingLotto(): Stream<Lotto> {
            return Stream.of(
                Lotto(listOf(LottoNumbers(listOf(1, 12, 13, 7, 8, 9).map(::LottoNumber)))),
                Lotto(listOf(LottoNumbers(listOf(1, 2, 13, 7, 8, 9).map(::LottoNumber)))),
                Lotto(listOf(LottoNumbers(listOf(11, 12, 13, 7, 8, 9).map(::LottoNumber))))
            )
        }
    }
}
