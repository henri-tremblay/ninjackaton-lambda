package com.ninja_squad.training.lambda;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests unitaires du TP Lambda
 * @author JB
 */
class TPTest {

    private void executeWithWrappedSysout(Runnable block) {
        PrintStream original = System.out;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             PrintStream out = new PrintStream(baos)) {

            System.setOut(out);
            block.run();
            String s = baos.toString();
            assertThat(s).isEqualTo("""
                    2012-01-12T10:00
                    2012-01-12T11:00
                    2012-01-12T12:00
                    2012-01-13T14:00
                    2012-01-14T18:00
                    2012-01-15T22:00
                    """);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            System.setOut(original);
        }
    }

    @Test
    void step1() {
        executeWithWrappedSysout(TP::step1);
    }

    @Test
    void step2() {
        executeWithWrappedSysout(TP::step2);
    }

    @Test
    void step3() {
        List<String> senders = TP.step3();

        assertThat(senders).containsExactly("@clacote",
                                   "@cedric_exbrayat",
                                   "@jbnizet",
                                   "@agnes_crepet",
                                   "@brian_goetz",
                                   "@jbnizet");
    }

    @Test
    void step4() {
        List<String> senders = TP.step4();

        assertThat(senders).containsExactly("@clacote",
                                   "@cedric_exbrayat",
                                   "@jbnizet",
                                   "@agnes_crepet",
                                   "@brian_goetz");
    }

    @Test
    void step5() {
        List<String> senders = TP.step5();

        assertThat(senders).containsExactly("@agnes_crepet",
                                   "@brian_goetz",
                                   "@cedric_exbrayat",
                                   "@clacote",
                                   "@jbnizet");
    }

    @Test
    void step6() {
        List<Tweet> lambdaTweets = TP.step6();

        assertThat(lambdaTweets.stream().map(Tweet::id)).containsExactly(1L, 2L, 3L, 6L);
    }

    @Test
    void step7() {
        List<Tweet> lambdaTweets = TP.step7();

        assertThat(lambdaTweets.stream().map(Tweet::id)).containsExactly(2L, 1L, 3L, 6L);
    }

    @Test
    void step8() {
        Set<String> hashTags = TP.step8();

        assertThat(hashTags).containsExactlyInAnyOrder("#baby", "#lambda", "#suicide", "#JDK8");
    }

    @Test
    void step9() {
        Map<String, List<Tweet>> tweetsBySender = TP.step9();

        assertThat(tweetsBySender).containsOnlyKeys("@brian_goetz", "@cedric_exbrayat", "@clacote", "@agnes_crepet", "@jbnizet");
        assertThat(tweetsBySender.get("@brian_goetz").stream().map(Tweet::id)).containsExactly(5L);
        assertThat(tweetsBySender.get("@cedric_exbrayat").stream().map(Tweet::id)).containsExactly(2L);
        assertThat(tweetsBySender.get("@clacote").stream().map(Tweet::id)).containsExactly(1L);
        assertThat(tweetsBySender.get("@agnes_crepet").stream().map(Tweet::id)).containsExactly(4L);
        assertThat(tweetsBySender.get("@jbnizet").stream().map(Tweet::id)).containsExactly(3L, 6L);
    }

    @Test
    void step10() {
        Map<Boolean, List<Tweet>> partition = TP.step10();
        Collection<Tweet> withLambda = partition.get(true);
        Collection<Tweet> withoutLambda = partition.get(false);

        assertThat(withLambda.stream().map(Tweet::id)).containsExactly(1L, 2L, 3L, 6L);
        assertThat(withoutLambda.stream().map(Tweet::id)).containsExactly(4L, 5L);
    }

    @Test
    public void step11() {
        assertThat(TP.step11()).isEqualTo(188);
    }

    @Test
    public void step12() {
        assertThat(TP.step12()).isEqualTo(31);
    }

    @Test
    public void step13() {
        assertThat(TP.step13()).isEqualTo(31);
    }

    @Test
    public void step14() {
        assertThat(TP.step14()).isEqualTo(31);
    }
}
