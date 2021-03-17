package lotto.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class WinningLottoTest {

    @Test
    fun `당첨로또의 당첨번호 중에 보너스번호가 존재하는 경우 예외를 반환한다`() {
        val lotto = Lotto((1..6).map { LottoNumber(it) })
        val bonusNumber = LottoNumber(1)
        val expectedMessage = "당첨번호 중에 보너스번호가 존재할 수 없습니다."

        val result = assertThrows<IllegalArgumentException> { WinningLotto(lotto, bonusNumber) }

        Assertions.assertThat(result.message).isEqualTo(expectedMessage)
    }
}
