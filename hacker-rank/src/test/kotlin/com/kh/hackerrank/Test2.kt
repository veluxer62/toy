package com.kh.hackerrank

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.math.abs

internal class Test2 {

    @Test
    fun test() {
        val word = "hackerrank"
        val k = 0
        val actual = longestKInterspaceSubstring(word, k)
        Assertions.assertEquals("rr", actual)
    }

    @Test
    fun test2() {
        val word = "ababbaca"
        val k = 1
        val actual = longestKInterspaceSubstring(word, k)
        Assertions.assertEquals("ababba", actual)
    }

    @Test
    fun test3() {
        val word = "bhljuexsyuuvyhztsimgvoxiyycuzeasmzghajvrjnykbdhyreufwirlccjhtfjuqvzidfdyidtxnikriwrvtoqenazltkbjstmrwtgdkwvschwpgfzqsacuuvjfpadbohqnywgsntoqquburdtpfpptrkfcsdcevbmcmhecxncrdirkwbmvqgynabprmdbjwhbcrncwdukokijzenidfqcrezgfxvatqffxezguqyztqargorrzknklmjfikrvatzstlmwjjrypjimkwzybcbinumkoiosqypajdajcphqdndcmvygsjjfreddemldzurfeeelztblokbfprielfojcgilsrxalovpxjchpufxerhhbgnhjjsrupofxjrimgndpnomkyzfqiqjxqxbtvmxvyrzmsgzwcgqquekxqjwfudqzydncuizvkzawcteorormmaqoiacevzqqjrwfhoshrepkcynkwdrkoeqesurqtzcqcdaonfsuwcuaokhimsynoyfwprdmhyesfpcxnmurltmwqwwenzmxjgopkcifomcjonllrdpwmtzubsoinwnlqcszsealyvnhwjnnuxkwyiopsmtvivjbcrkcxsrmopbchayttisbzxktfxwenesgysuwvkfdwkagbdlpfzayxlntwykyyuewfzifjwwjnxjbknzfqezipnkkjkrnqethkpblnqusmhdfhkjkvelytlqnltmbbsurveyegehkofkutdekhemnmyqvhwwurhsuounbjzwhicxibfghjykectxtdyfhyybljnfvowjaizjffzpxnwcqahlzlfikhslacbfghdwvtqfqqxbqbmxksbiiypozbsyybmdzfrhxprmmgmmrpmtcajbsdstjjowhqwrsuzzvttmmksmsxghtsqgesrczefrzcfprtxvegkuzlypsjbhahbilioibyxifzjpzsrjpfelrzktziuojdbmdnknjzorzcawbsalusvdufbnamdirsmuijsmuuugnqovtwqgutqhpvqwlyycygirhusujqqvnpvwqsgstoscmclnbfytsuxzclhsfzfxkxsfjxkvabuttbscomybdepwrqndoppvtikpmqkdsjixsocbzbswooayozoyrzyhbushytgvprajdcstirvvwkaznlhcxdqejuqpjuhwfqeseigscrstbxqvyfpnwxztvhqxgbbslffitlsxdqvaextbexuwwdiejfsmdmgfpounfywkuyypnyucmibazrcbivlwzszsfjhvkvpcbqbuqdllgcvqjibrfazennnmffpyheodzfldmnradktpbjkkpqvyhygxlunqhqtxnadioghyjomokbtavxyfbidmelfqrsfxekbqxuuwehghvbnavvjrgdjgczvfcponryacggjmfzvalipnpmxbjefhujbgymqjaullceylijkaspltedadskhefbkqrqtsgbdtxwkzyaqyqemwcjysfkvzwgqcnuzgendculxttrdsxsxmxqfdgutpnyjolknsiuezcvujxlpotblpmpgjjvukpjitayuahcttxdmomludxesiutmpxnpqnrkolmbsdtdoksakpgfyozgnsgtbfbedwoghhhyndyzpezihblyfvigwhkqfygzwcbmbpiimeogzoqhdwisfanuncucocgaqwkobzfidbbqgmaqqzdvfafmczdhuzkkmcvjgejgayrjarlurbmhvhosojxqdzznqpkropeexnjtsqkaienkgcibbtxashzzdnfdliciyyghecxlebapsexthlfulocnrsmlcqgkaqslqfiapoeuqrzjfswmonxlixmitdpafnndsifnrvdrsddkgdniojhnpmokhhiverylrhdrxyoqhbxnpsiktchnrhlbzahwzmjjvedyrtldihjetjwpycxpoccatlxigqodlsakvgetsaoubsezeyszapiyapwuaeisdzckiuuxtisywjsnhtlzptnojwbckpnabscvivbqdtgmgdsxqpxrebdovsgbksxugusjncvwqhwvaeezmkajhjiebbdpgtfqrnrjequlgeynvtrygkeqpqmjaigoepbfqguqkadvesqhcxmfsgilnlqcopfjekzzgtbymrfxkserpbwbcnpkkwhqfojvtpeuxjyiyitmgvyqfszsqgsmspbqoozqhisbwosdphwrdnxpknoqjiyavzfrvzwtlvqmuccymzivacvmqmbrwnthqqwadbpxhngtvvzvqieetfymhqedcxwhpkonbjnkudvfivhebhsnzsxzzjaddyqpktcvkgfejyixagxbdsicgvwqtlroyvxvgjdtvuukpniubgeenyppzyhsewdpocrchiqhcngcfopcbykcttgfwdfniwhzjnzoofmamkcuqgpmeywjfqfowfqxlyryekxogkjtowfjxdgowypixismveecselbbryhtswxgkfrydldvekuvkvsjcavjvhhclhavrywrckyfhwmzmqfbtbigbyjgrrkehtkmyhhtwnzjjktyvzbaqyuusdfguliqqkxuiqhkedftupgkmocgiroddijslfinxhytanzqqlhptrnybbuuyuthofrlwbzxsftflqwblleaecbzhdpqdmyxqgjvnetpyyzwefgbvsbjgbmrhqczriujldtqpolsmdgehwsmrczmfgbugfiomwfbwpxwvluaorgczsgxkzotrlsdkodqczcpvjhcpporirercxgxfwjekvvonigchggrzjueyskjhzokxmzkldnjcimunboojaqzvcdpmsonsfoowlreikclgqjamjvtmdskdpyotvojllpxoqxfzkgfhvmckjlhapayylkdiiptotsrjvyhmcomxsbyxqxxqpgfgenxeqndjcjlvqxsqioyvqgleazxszwccdkpzwmlzmasoaaryphmijhjuqpgwpunuimyiaylayazpnulmsphyqjxroujvoyddahutpacfuebouhppvrsavfkandiqeuoedxkqfkutogoesxhfsnvknivrieoojvsbvcuwhpjjthljozuatuoihddpluzxxnklwrkmkhnrehgbnjsmbcenyjcrwyzzgaqsfouwimwjlqgpygkqtsizszroumhkecovugpbhbiezjgduyilhyphsxkcbowigfpusgpcuopbtvhkofupvxklrfqemwjymbxdtauvmlmhzqtwqgryrvuaykxryxjbiqicsgyksoojojlqexosacjbjrplryelvmnrqodzemnbavywgwbuhffigvudtnqdtdkdxzxihlsvypqkogiylxvchdbruosugupkmdwkuepzkvmrnsylccpduzgvwndlepfvyhtytkggxrzofupbupqcjzxwdvxufiqwguwulkrlpzlxqbnxtdwuiemmpumhakpuchklyrtiorrqzapnwdhzuvcbzttlxygpnragvawbhkfrpqskskmkpiypqgymlqicwvflfltkkejqotaydqbdflqpnolwdasqvkxbvvfmasnwwhwqufkfnisaxzynjjddrxgvxktyiogjiwnyrulrxthfkbecgwbldqbpbzzudvajozhmlxtkiltqcclgfdmasdietvjglejokfociswlqxtbfhyoxwuronqlgjfjkphapcmcnbtgfgvludaoyhxseakzasslxvatvlejvsladkvjjmetfwpnnlorpemmamqhmpgkzzbxxzqwmbwfycgwemvvpyeoycgfsvwmutczfqwtusngsdktdufrycyfinzrvjflbzbpvreypnqhzzpkmnyzqqwvisnivsuozvdmtgzhzesfmxcyblrsdpqkxyqovfseuebeysdiqflwnjzplnserntnodwzmfvnatoxsjrclzfzhxjqqmnqdzxwdcssgeblgrezgwknapvbsppitfedryvzxqdzxvdiyuajycpbxxkpkhdekgxhyjzgcheedyszlihjfossmjdbouwttystfvpkbhcdyxmzeyqibqledsvjnyffidkkwbourovzndkfxnlpsizxkemavarmqelsnpgjezgfacbxzgxpvimbmambsvwmqbzilvpqjtcmbgerufbtbeqpmfgiwrughddpejuornucejnyfijstrzoneizbrgogturzfljgckyxdngrvawclptfrnvrwrwvurzmvxtkixllivrkcfxlguvlmngyfcdjqflggiunvhvuzkxhvqnwymjztermppcxqrgeobrbpargqlraqrjwkfnzgnudranaxqfmsaantchudfhvmuwfrbhmssgzxdxrfkxrytlbyaeflklvxhzpvafeunvqeiadxxclrotkfcymnkgygghtkuacbaoazpsbxjzmfidjpsaoiozjvulmcuzguyhphrgietadphlirgbmvmonsljumqrlengezdpchpfkwoyteprqyjbjupamjxeovuhueuxeuxfgnltehtouiepnlmrgltedeokmmiwbonglzcuuzzzfyuwbollpptcijkmsmhueevchpiuprshexwdfmwfgxccblwrkapnvplvoknfcrulvfjfnyzmtfjummlhpippsmfqfocmhkdgizkrsacjysnqskvpungzhzdgxwejklehueubwyinmebajhezjrcgoqielfzwhnsdcrctmrochftxuxicbagbnesyhdspbazhfxbdjunghnnogatkkaknajopclxaeyhjcmufbaoubmspuwhsdazfikvszhyunqiavavyyqcqzcgzsvxomrenckzcogkgnjdoyawvshefm"
        val k = 22
        val actual = longestKInterspaceSubstring(word, k)
        Assertions.assertEquals("zpnulmsphyqjxroujvoyddahutpacfuebouhppvrsavfkandiqeuoedxkqfkutogoesxhfsnvknivrieoojvsbvcuwhpjjthljozuatuoihddpluzxxnklwrkmkhnrehgbnjsmbcenyjcrwyzzgaqsfouwimwjlqgpygkqtsizszroumhkecovugpbhbiezjgduyilhyphsxkcbowigfpusgpcuopbtvhkofupvxklrfqemwjymbxdtauvmlmhzqtwqgryrvua", actual)
    }
}

/*
가장 긴 K- 인터페이스 하위 문자열
한 문자열 내에 인접한 모든 문자 쌍의 아스키 값 절대 차이가 최대 k라면  k-인터페이스 문자열이라고 합니다.

예를 들어, "abac"은 k ≤ 2에 대한 k-인터페이스 문자열입니다. 왜냐하면 그것의 모든 인접한 문자(아스키 값) 사이의 절대 차이는 최대 2이기 때문입니다.



하나의 하위 문자열은 주어진 문자열 내의 연속된 문자들의 그룹입니다.

예를 들어,  'abc'의 하위 문자열은 [abc, ab, bc, a, b, c] 입니다.

만약 어떤 하위 문자열이 k- 인터페이스 조건을 만족한다면,  k-인터페이스 하위 문자열 이라고 합니다.



하나의 문자열 타입 word와 정수타입 k가 주어졌을 때,

문자열 word 내에서 가장 긴 k-인터페이스 하위 문자열을 찾으십시오.

가장 긴 길이의 k-인터페이스 하위 문자열이 여러 개 있는 경우, 가장 처음 조건에 맞는 하위 문자열을 반환하세요.



예시:

word = wedding

k = 0

가장 먼저 찾은 0-인터페이스 조건에 맞는 하위 문자열은 dd입니다.



word = ababbacaabbbb

k = 1

여기에 길이가 6인 1-인터페이스 하위 문자열이 2개가 있습니다 : ababba,  aabbbb

그 중, 가장 먼저 찾은 것은 ababba입니다.



함수 설명:

아래 편집기에서 longestKInterspaceSubstring 함수를 구현하세요.



longestKInterspaceSubstring 함수는 다음 인자를 받습니다:

    string word: 입력 문자열 word

    int k : 아스키 값의 최대 차이 k



반환값:

    string: 입력 문자열 word에서 가장 긴 k-인터페이스 중 첫번째로 일치한 하위 문자열.



제약 조건:

1 ≤ | word | ≤ 10^6

0 ≤ k ≤ 25

word내 각 문자 ∈ ascii[a-z]


 */
fun longestKInterspaceSubstring(word: String, k: Int): String {
    var result = ""
    val arr = word.toByteArray()

    for (i in 0..arr.size - 2) {
        if (abs(arr[i].toInt() - arr[i+1].toInt()) <= k) {
            if (result.isEmpty())
                result += arr[i].toChar()
            result += arr[i + 1].toChar()
        }
    }

    return result
}