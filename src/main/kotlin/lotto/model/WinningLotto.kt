package lotto.model

import lotto.model.Lotto.Companion.NUMBER_OF_LOTTO

data class WinningLotto(
    val numbers: List<LottoNumber>,
) {
    init {
        require(numbers.size == NUMBER_OF_LOTTO) { "당첨 번호는 ${NUMBER_OF_LOTTO}개의 숫자여야 합니다." }
        require(numbers.distinct().size == NUMBER_OF_LOTTO) { "당첨 번호는 중복되는 숫자가 없어야 합니다." }
    }
}
